package gatlingdemostoreapi;

import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class DemostoreApiSimulationPostman extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("https://demostore.gatling.io")
        .header("Cache-Control", "no-cache")
        .contentTypeHeader("application/json")
        .acceptHeader("application/json");

    private Map<CharSequence, String> authorizationHeaders = Map.ofEntries(
        Map.entry("authorization", "Bearer #{jwt}")
    );

    private ScenarioBuilder scn = scenario("DemostoreApiSimulationPostman")
        .exec(
            http("Get all categories")
                .get("/api/category")
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0000_response.json")))
        )
        .pause(2)
        .exec(
            http("Get a specific category")
                .get("/api/product?category=7")
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0001_response.json")))
        )
        .pause(2)
        .exec(
            http("Get a product")
                .get("/api/product/34")
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0002_response.json")))
        )
        .pause(2)
        .exec(
            http("Authenticate")
                .post("/api/authenticate")
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0003_request.json"))
                .check(jsonPath("$.token").saveAs("jwt"))
        )
        .pause(2)
        .exec(
            http("Update a product")
                .put("/api/product/34")
                .headers(authorizationHeaders)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0004_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0004_response.json")))
        )
        .pause(2)
        .exec(
            http("Create a new product")
                .post("/api/product")
                .headers(authorizationHeaders)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0005_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0005_response.json")))
        )
        .pause(2)
        .exec(
            http("Create a new product")
                .post("/api/product")
                .headers(authorizationHeaders)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0006_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0006_response.json")))
        )
        .pause(2)
        .exec(
            http("Create a new product")
                .post("/api/product")
                .headers(authorizationHeaders)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0007_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0007_response.json")))
        )
        .pause(2)
        .exec(
            http("Update a category")
                .put("/api/category/7")
                .headers(authorizationHeaders)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0008_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulationpostman/0008_response.json")))
        );

    {
        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }
}
