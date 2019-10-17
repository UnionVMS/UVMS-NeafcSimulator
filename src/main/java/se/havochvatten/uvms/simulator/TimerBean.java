package se.havochvatten.uvms.simulator;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.scheduler.Scheduled;

// start in dev mode :  mvnw compile quarkus:dev  (remote debug on 5005)
// build             :  mvnw clean package   -> output in target as usual
// execute           :  java -jar UVMS-NeafcSimulator-1.0-SNAPSHOT-runner
// stop              :  ctrl-c


// list available extentions mvnw quarkus:list-extensions
// add an extention if needed : mvnw quarkus:add-extension -Dextensions="hibernate-validator"


@ApplicationScoped
public class TimerBean {

    LatLong position = new LatLong(58.973, 5.781, Date.from(Instant.now().minusMillis(10 * 60 * 1000)));
    AssetDTO asset = createTestAsset();



    @Scheduled(every="1s")
    void sendPosition() {
        try {
            NafHelper.sendPositionToNAFPlugin(position, asset);
        } catch (IOException e) {
            System.out.println("ERROR");
            System.out.println(e.toString());
        }
    }

    private AssetDTO createTestAsset() {

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

    String generateARandomStringWithMaxLength(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            int val = new Random().nextInt(10);
            ret += String.valueOf(val);
        }
        return ret;
    }


}
