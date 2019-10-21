package se.havochvatten.uvms.simulator;

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

    public void sendPositionToNAFPlugin( LatLong position, AssetDTO asset) throws IOException {

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




    public List<TripPos> harbourToSea() {
        List<TripPos> t = new ArrayList<>();

        t.add(new TripPos( 63.440   ,  10.411));
        t.add(new TripPos( 63.442   ,  10.412));
        t.add(new TripPos( 63.444   ,  10.408));
        t.add(new TripPos( 63.443   ,  10.396));
        t.add(new TripPos( 63.447   ,  10.380));
        t.add(new TripPos( 63.450   ,  10.366));
        t.add(new TripPos( 63.455   ,  10.388));
        t.add(new TripPos( 63.459   ,  10.299));
        t.add(new TripPos( 63.460   ,  10.282));
        t.add(new TripPos( 63.461   ,  10.265));
        t.add(new TripPos( 63.464   ,  10.236));
        t.add(new TripPos( 63.465   ,  10.198));
        t.add(new TripPos( 63.446   ,  10.139));
        t.add(new TripPos( 63.459   ,  9.993));
        t.add(new TripPos( 63.562   ,  9.872));
        t.add(new TripPos( 63.650   ,  9.790));
        t.add(new TripPos( 63.594   ,  9.548));
        t.add(new TripPos( 63.494   ,  9.166));
        t.add(new TripPos( 63.420   ,  8.828));
        t.add(new TripPos( 63.365   ,  8.496));
        t.add(new TripPos( 63.325   ,  8.367));
        t.add(new TripPos( 63.513   ,  8.180));
        t.add(new TripPos( 63.650   ,  7.963));
        t.add(new TripPos( 63.729   ,  7.757));
        t.add(new TripPos( 63.763   ,  7.667));

        return t;
    }

    public List<TripPos> seaToHarbour() {
        List<TripPos> t = harbourToSea();
        Collections.reverse(t);;
        return t;
    }



    public List<TripPos> generateTrondheimTrip() {
        List<TripPos> t = harbourToSea();

        double latitude = 64.041;
        double longitude = -0.280;

        int position = 0;
        int n = 13;

        while(position < n){
            t.add(new TripPos(latitude, longitude));
            latitude += 1;
            position++;
        }

        longitude = longitude + 0.5;

        while(position >= 0){
            t.add(new TripPos(latitude, longitude));
            latitude -= 1;
            position--;
        }

        t.addAll(seaToHarbour());

        return t;

    }


    public double calcSpeed(LatLong src, LatLong dst) {
        try {
            if (src.positionTime == null)
                return 0;
            if (dst.positionTime == null)
                return 0;
            // distance to next
            double distanceM = src.distance;
            double durationMs = (double) Math.abs(dst.positionTime.getTime() - src.positionTime.getTime());
            double durationSecs = durationMs / 1000;
            double speedMeterPerSecond = (distanceM / durationSecs);
            double speedMPerHour = speedMeterPerSecond * 3600;
            return speedMPerHour / 1000;
        } catch (RuntimeException e) {
            return 0.0;
        }
    }

    public  double getBearing(List<TripPos> aTrip, int step) {
        /**
         * Formula: θ = atan2( sin(Δlong).cos(lat2), cos(lat1).sin(lat2) −
         * sin(lat1).cos(lat2).cos(Δlong) )
         */

        TripPos forePoint = aTrip.get(step);
        TripPos standPoint = null;
        if(step == 0){
            standPoint = aTrip.get(step);
        } else{
            standPoint = aTrip.get(step - 1);
        }


        double y = sin(toRadians(forePoint.longitude - standPoint.longitude)) * cos(toRadians(forePoint.latitude));
        double x = cos(toRadians(standPoint.latitude)) * sin(toRadians(forePoint.latitude))
                - sin(toRadians(standPoint.latitude)) * cos(toRadians(forePoint.latitude)) * cos(toRadians(forePoint.longitude - standPoint.longitude));
        double bearing = (atan2(y, x) + 2 * PI) % (2 * PI);
        return toDegrees(bearing);
    }






}
