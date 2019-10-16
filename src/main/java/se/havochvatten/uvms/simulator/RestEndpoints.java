package se.havochvatten.uvms.simulator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/neafcsimulator")
public class RestEndpoints {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from UVMS-NeafcSimulator.";
    }
}