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
    AssetDTO asset = NafHelper.createTestAsset();



    @Scheduled(every="1m")
    void sendPosition() {
        try {

            position.longitude += 0.01;
            position.latitude += 0.01;
            position.positionTime = Date.from(Instant.now().minusMillis(10 * 60 * 1000));

            NafHelper.sendPositionToNAFPlugin(position, asset);
        } catch (IOException e) {
            System.out.println("ERROR");
            System.out.println(e.toString());
        }
    }




}
