package se.havochvatten.uvms.simulator;

import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

// start in dev mode :  mvnw compile quarkus:dev  (remote debug on 5005)
//                      mvnw compile quarkus:dev -Dsuspend  (wait for debugger attach)
// build             :  mvnw clean package   -> output in target as usual
// execute           :  java -jar UVMS-NeafcSimulator-1.0-SNAPSHOT-runner
// stop              :  ctrl-c
//


// list available extentions mvnw quarkus:list-extensions
// add an extention if needed : mvnw quarkus:add-extension -Dextensions="hibernate-validator"

@ApplicationScoped
public class TimerBean {

    private final Logger LOG = LoggerFactory.getLogger(TimerBean.class);

    @Inject
    Functions functions;

    AssetDTO asset;
    List<TripPos> aTrip;
    LatLong position;
    int tripStep = 0;

    @PostConstruct
    public void init() {
        LOG.info("init");
        asset = functions.createTestAsset();
        aTrip = functions.generateTrondheimTrip();
        position = new LatLong(76.258, -1.7578, Date.from(Instant.now().minusMillis(10 * 60 * 1000)));
    }

    @PreDestroy
    public void exit() {
        LOG.info("exit");
        aTrip.clear();
    }

    private void calcPosition() {
        position.longitude = aTrip.get(tripStep).longitude;
        position.latitude = aTrip.get(tripStep).latitude;
        position.positionTime = Date.from(Instant.now().minusMillis(10 * 60 * 1000));
        position.bearing = functions.getBearing(aTrip, tripStep);
        tripStep++;
        if (tripStep >= aTrip.size()) {
            tripStep = 0;
        }
    }


    @Scheduled(every = "2m")
    void sendPosition() {

        try {
            calcPosition();
            functions.sendPositionToNAFPlugin( position, asset);
        } catch (IOException e) {
            LOG.error(e.toString(), e);
        }
    }

}
