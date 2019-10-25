package se.havochvatten.uvms.simulator;

import io.quarkus.test.junit.QuarkusTest;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RestEndpointsTest {

    @Test
    @Ignore
    public void testHelloEndpoint() {
        given()
          .when().get("/neafcsimulator")
          .then()
             .statusCode(200)
             .body(is("Hello from UVMS-NeafcSimulator."));
    }

}