package se.havochvatten.uvms.simulator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NafHelper  {

    public static void sendPositionToNAFPlugin(LatLong position, AssetDTO asset) throws IOException {

        String nafString = convertToNafString(position, asset);
        String requestPath = "http://localhost:28080/naf/rest/message/" + nafString;
        Client client = ClientBuilder.newClient();
        try {
            client.target(requestPath).request(MediaType.APPLICATION_JSON).get();
        }
        finally{

            client.close();
        }
    }

    private static String convertToNafString(LatLong position, AssetDTO asset) throws UnsupportedEncodingException {
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

    public static String readCodeValue(String code, String nafMessage) {
        Pattern pattern = Pattern.compile("//" + code + "/" + "([^" + "/" + "]+)" + "//");
        Matcher matcher = pattern.matcher(nafMessage);
        matcher.find();
        return matcher.group(1);
    }


    public static  AssetDTO createTestAsset() {

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

    private static String generateARandomStringWithMaxLength(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            int val = new Random().nextInt(10);
            ret += String.valueOf(val);
        }
        return ret;
    }


    public static List<TripPos> generateAtrip() {

        List<TripPos> t = new ArrayList<>();
        t.add(new TripPos(77.118,-0.263));
        t.add(new TripPos(76.118,-0.263));
        t.add(new TripPos(75.118,-0.263));
        t.add(new TripPos(74.118,-0.263));
        t.add(new TripPos(73.118,-0.263));
        t.add(new TripPos(72.118,-0.263));
        t.add(new TripPos(71.118,-0.263));
        t.add(new TripPos(70.118,-0.263));
        t.add(new TripPos(69.118,-0.263));
        t.add(new TripPos(68.118,-0.263));
        t.add(new TripPos(67.118,-0.263));
        t.add(new TripPos(66.118,-0.263));
        t.add(new TripPos(65.118,-0.263));

        t.add(new TripPos(66.118,-0.263));
        t.add(new TripPos(67.118,-0.263));
        t.add(new TripPos(68.118,-0.263));
        t.add(new TripPos(69.118,-0.263));
        t.add(new TripPos(70.118,-0.263));
        t.add(new TripPos(71.118,-0.263));
        t.add(new TripPos(72.118,-0.263));
        t.add(new TripPos(73.118,-0.263));
        t.add(new TripPos(74.118,-0.263));
        t.add(new TripPos(75.118,-0.263));
        t.add(new TripPos(76.118,-0.263));
        t.add(new TripPos(77.118,-0.263));
        t.add(new TripPos(78.118,-0.263));

        t.add(new TripPos(77.118,-0.163));
        t.add(new TripPos(76.118,-0.163));
        t.add(new TripPos(75.118,-0.163));
        t.add(new TripPos(74.118,-0.163));
        t.add(new TripPos(73.118,-0.163));
        t.add(new TripPos(72.118,-0.163));
        t.add(new TripPos(71.118,-0.163));
        t.add(new TripPos(70.118,-0.163));
        t.add(new TripPos(69.118,-0.163));
        t.add(new TripPos(68.118,-0.163));
        t.add(new TripPos(67.118,-0.163));
        t.add(new TripPos(66.118,-0.163));
        t.add(new TripPos(65.118,-0.163));

        t.add(new TripPos(66.118,-0.163));
        t.add(new TripPos(67.118,-0.163));
        t.add(new TripPos(68.118,-0.163));
        t.add(new TripPos(69.118,-0.163));
        t.add(new TripPos(70.118,-0.163));
        t.add(new TripPos(71.118,-0.163));
        t.add(new TripPos(72.118,-0.163));
        t.add(new TripPos(73.118,-0.163));
        t.add(new TripPos(74.118,-0.163));
        t.add(new TripPos(75.118,-0.163));
        t.add(new TripPos(76.118,-0.163));
        t.add(new TripPos(77.118,-0.163));
        t.add(new TripPos(78.118,-0.163));
        return t;

    }
}