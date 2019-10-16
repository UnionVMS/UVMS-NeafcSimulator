package se.havochvatten.uvms.simulator;

import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.scheduler.Scheduled;

// start in dev mode :  mvnw compile quarkus:dev
// build             :  mvnw clean package   -> output in target as usual
// execute           :  java -jar UVMS-NeafcSimulator-1.0-SNAPSHOT-runner
// stop              :  ctrl-c

@ApplicationScoped
public class TimerBean {
	
	private AtomicInteger counter = new AtomicInteger();
	
	public int get() {  
        return counter.get();
    }

    @Scheduled(every="10s")     
    void increment() {
        counter.incrementAndGet(); 
        System.out.println("counter : " + counter.get());
    }

}
