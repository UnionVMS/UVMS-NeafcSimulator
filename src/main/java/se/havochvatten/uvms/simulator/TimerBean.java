package se.havochvatten.uvms.simulator;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
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

    LatLong position = new LatLong(76.258, -1.7578, Date.from(Instant.now().minusMillis(10 * 60 * 1000)));
    AssetDTO asset = NafHelper.createTestAsset();
    List<Trip> aTrip;
    int tripStep = 0;

    public TimerBean(){
        aTrip = NafHelper.generateAtrip();
    }

    private void calcPosition(){
        position.longitude = aTrip.get(tripStep).longitude;
        position.latitude = aTrip.get(tripStep).latitude;
        position.positionTime = Date.from(Instant.now().minusMillis(10 * 60 * 1000));
        tripStep++;
        if(tripStep >= aTrip.size()){
            tripStep = 0;
        }
    }



    @Scheduled(every="2m")
    void sendPosition() {
        try {
            calcPosition();
            NafHelper.sendPositionToNAFPlugin(position, asset);
        } catch (IOException e) {
            System.out.println("ERROR");
            System.out.println(e.toString());
        }
    }




}
