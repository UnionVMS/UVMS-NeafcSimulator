package se.havochvatten.uvms.simulator;

import com.peertopark.java.geocalc.Coordinate;
import com.peertopark.java.geocalc.DegreeCoordinate;
import com.peertopark.java.geocalc.EarthCalc;
import com.peertopark.java.geocalc.Point;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.cos;
import static java.lang.StrictMath.*;

@Dependent
public class Functions {

    private final Logger LOG = LoggerFactory.getLogger(Functions.class);

    @ConfigProperty(name = "simulator.nafurl")
    public String nafUrl;

    public String convertToNafString(LatLong position, AssetDTO asset) throws UnsupportedEncodingException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        dateFormatter.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmm");
        timeFormatter.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));

        DecimalFormat formatter = new DecimalFormat("#00.000");

        String str = "//SR" +
                "//FR/" + asset.getFlagStateCode() +
                "//AD/UVM" +
                "//TM/POS" +
                "//RC/" + asset.getIrcs() +
                "//IR/" + asset.getCfr() +
                "//XT/" + asset.getExternalMarking() +
                "//LT/" + formatter.format(position.latitude).replace(",", ".") +
                "//LG/" + formatter.format(position.longitude).replace(",", ".") +
                "//SP/" + (int) (position.speed * 10) +
                "//CO/" + (int) position.bearing +
                "//DA/" + dateFormatter.format(position.positionTime) +
                "//TI/" + timeFormatter.format(position.positionTime) +
                "//NA/" + asset.getName() +
                "//FS/" + asset.getFlagStateCode() +
                "//ER//";
        return URLEncoder.encode(str, "UTF-8");
    }

    public String readCodeValue(String code, String nafMessage) {
        Pattern pattern = Pattern.compile("//" + code + "/" + "([^" + "/" + "]+)" + "//");
        Matcher matcher = pattern.matcher(nafMessage);
        matcher.find();
        return matcher.group(1);
    }

    public AssetDTO createTestAsset() {

        AssetDTO asset = new AssetDTO();

        asset.setActive(true);
        asset.setName("Ship" + generateARandomStringWithMaxLength(10));
        asset.setCfr("CFR" + generateARandomStringWithMaxLength(9));
        asset.setFlagStateCode("SWE");
        asset.setIrcsIndicator(true);
        asset.setIrcs("F" + generateARandomStringWithMaxLength(7));
        asset.setExternalMarking("EXT3");
        asset.setImo("0" + generateARandomStringWithMaxLength(6));
        asset.setMmsi(generateARandomStringWithMaxLength(9));

        asset.setSource("INTERNAL");

        asset.setMainFishingGearCode("DERMERSAL");
        asset.setHasLicence(true);
        asset.setLicenceType("MOCK-license-DB");
        asset.setPortOfRegistration("TEST_GOT");
        asset.setLengthOverAll(15.0);
        asset.setLengthBetweenPerpendiculars(3.0);
        asset.setGrossTonnage(200.0);

        asset.setGrossTonnageUnit("OSLO");
        asset.setSafteyGrossTonnage(80.0);
        asset.setPowerOfMainEngine(10.0);
        asset.setPowerOfAuxEngine(10.0);

        return asset;
    }

    private String generateARandomStringWithMaxLength(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            int val = new Random().nextInt(10);
            ret += String.valueOf(val);
        }
        return ret;
    }

    public void sendPositionToNAFPlugin(LatLong position, AssetDTO asset) throws IOException {

        String nafString = convertToNafString(position, asset);
        LOG.info("send position to " + nafUrl);

        String requestPath = nafUrl + "naf/rest/message/" + nafString;
        Client client = ClientBuilder.newClient();
        try {
            client.target(requestPath).request(MediaType.APPLICATION_JSON).get();
        } finally {
            client.close();
        }
    }


    public List<LatLong> generateTrip() {
        List<LatLong> t = new ArrayList<>();
        List<TripPos> trip = generateTrondheimTrip();

        for (int i = 0; i < trip.size(); i++) {
            TripPos tripPos = trip.get(i);
            LatLong position = new LatLong(tripPos.latitude, tripPos.longitude, null);
            position.bearing = calcBearing(trip, i);
            t.add(position);
        }
        return t;
    }


    private List<TripPos> harbourToSea() {
        List<TripPos> t = new ArrayList<>();

        t.add(new TripPos(63.4397, 10.4114));
        t.add(new TripPos(63.4475, 10.4183));
        t.add(new TripPos(63.4426, 10.3961));
        t.add(new TripPos(63.4465, 10.3802));
        t.add(new TripPos(63.4561, 10.3352));
        t.add(new TripPos(63.4550, 10.1325));
        t.add(new TripPos(63.4458, 9.9965));
        t.add(new TripPos(63.5078, 9.8540));
        t.add(new TripPos(63.6661, 9.7936));
        t.add(new TripPos(63.3383, 8.3448));
        t.add(new TripPos(63.4873, 8.2034));
        t.add(new TripPos(63.6731, 7.9452));
        t.add(new TripPos(63.763, 7.667));

        return t;
    }

    private List<TripPos> seaToHarbour() {
        List<TripPos> t = harbourToSea();
        Collections.reverse(t);
        return t;
    }


    private List<TripPos> generateTrondheimTrip() {
        List<TripPos> t = harbourToSea();

        double latitude = 64.041;
        double longitude = -0.280;

        int position = 0;
        int n = 13;

        while (position < n) {
            t.add(new TripPos(latitude, longitude));
            latitude += 1;
            position++;
        }

        longitude = longitude + 0.5;

        while (position >= 0) {
            t.add(new TripPos(latitude, longitude));
            latitude -= 1;
            position--;
        }

        t.addAll(seaToHarbour());

        return t;

    }

    public double calcSpeed(List<LatLong> trip, int tripStep) {

        if (tripStep == 0) {
            return 0.0d;
        } else {
            return calcSpeed(trip.get(tripStep - 1), trip.get(tripStep));
        }
    }


    private double calcSpeed(LatLong src, LatLong dst) {
        try {
            if (src.positionTime == null)
                return 0;
            if (dst.positionTime == null)
                return 0;
            // distance to next
            double distanceM = distance(src,  dst);
            double durationMs = (double) Math.abs(dst.positionTime.getTime() - src.positionTime.getTime());
            double durationSecs = durationMs / 1000;
            double speedMeterPerSecond = (distanceM / durationSecs);
            double speedMPerHour = speedMeterPerSecond * 3600;
            return speedMPerHour / 1000;
        } catch (RuntimeException e) {
            return 0.0;
        }
    }

    private Double distance(LatLong src, LatLong dst) {

        Coordinate latFrom = new DegreeCoordinate(src.latitude);
        Coordinate lngFrom = new DegreeCoordinate(src.longitude);
        Point from = new Point(latFrom, lngFrom);

        Coordinate latTo = new DegreeCoordinate(dst.latitude);
        Coordinate lngTo = new DegreeCoordinate(dst.longitude);
        Point to = new Point(latTo, lngTo);

        return EarthCalc.getDistance(from, to);
    }


    public double calcBearing(List<TripPos> aTrip, int step) {
        /**
         * Formula: θ = atan2( sin(Δlong).cos(lat2), cos(lat1).sin(lat2) −
         * sin(lat1).cos(lat2).cos(Δlong) )
         */

        TripPos forePoint = aTrip.get(step);
        TripPos standPoint = null;
        if (step == 0) {
            standPoint = aTrip.get(step);
        } else {
            standPoint = aTrip.get(step - 1);
        }
        double y = sin(toRadians(forePoint.longitude - standPoint.longitude)) * cos(toRadians(forePoint.latitude));
        double x = cos(toRadians(standPoint.latitude)) * sin(toRadians(forePoint.latitude))
                - sin(toRadians(standPoint.latitude)) * cos(toRadians(forePoint.latitude)) * cos(toRadians(forePoint.longitude - standPoint.longitude));
        double bearing = (atan2(y, x) + 2 * PI) % (2 * PI);
        return toDegrees(bearing);
    }


}
