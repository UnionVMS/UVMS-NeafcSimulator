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
// mvnw quarkus:generate-config -Dfile=TESTapplication.properties


// list available extentions mvnw quarkus:list-extensions
// add an extention if needed : mvnw quarkus:add-extension -Dextensions="hibernate-validator"

@ApplicationScoped
public class TimerBean {

    private final Logger LOG = LoggerFactory.getLogger(TimerBean.class);

    @Inject
    Functions functions;

    AssetDTO asset;
    List<LatLong> aTrip;
    int tripStep = 0;

    @PostConstruct
    public void init() {
        LOG.info("init");
        asset = functions.createTestAsset();
        aTrip = functions.generateTrip();
    }

    @PreDestroy
    public void exit() {
        LOG.info("exit");
        aTrip.clear();
    }

    private  LatLong  calcPosition() {

        aTrip.get(tripStep).positionTime = Date.from(Instant.now().minusMillis(10 * 60 * 1000));
        LatLong position = aTrip.get(tripStep);
        position.speed = functions.calcSpeed(aTrip, tripStep);
        LOG.info(position.toString());
        tripStep++;
        if (tripStep >= aTrip.size()) {
            tripStep = 0;
        }
        return position;
    }


    @Scheduled(every = "60m")
    void sendPosition() {

        try {
            LatLong position = calcPosition();
            functions.sendPositionToNAFPlugin( position, asset);
        } catch (IOException e) {
            LOG.error(e.toString(), e);
        }
    }

}
