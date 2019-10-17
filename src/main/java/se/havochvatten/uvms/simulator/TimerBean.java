package se.havochvatten.uvms.simulator;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.config.inject.ConfigProperty;

// start in dev mode :  mvnw compile quarkus:dev  (remote debug on 5005)
// build             :  mvnw clean package   -> output in target as usual
// execute           :  java -jar UVMS-NeafcSimulator-1.0-SNAPSHOT-runner
// stop              :  ctrl-c


// list available extentions mvnw quarkus:list-extensions
// add an extention if needed : mvnw quarkus:add-extension -Dextensions="hibernate-validator"


public class TimerBean {

    NafHelper nafHelper = new NafHelper();
    AssetDTO asset;
    List<TripPos> aTrip;
    LatLong position ;
    int tripStep = 0;

    @ConfigProperty(name = "simulator.nafurl")
    public String nafUrl;

    public TimerBean(){
        asset = nafHelper.createTestAsset();
        aTrip = nafHelper.generateAtrip();
        position = new LatLong(76.258, -1.7578, Date.from(Instant.now().minusMillis(10 * 60 * 1000)));
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
            nafHelper.sendPositionToNAFPlugin(nafUrl, position, asset);
        } catch (IOException e) {
            System.out.println("ERROR");
            System.out.println(e.toString());
        }
    }




}
