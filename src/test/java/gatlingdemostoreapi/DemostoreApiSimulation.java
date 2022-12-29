package gatlingdemostoreapi;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class DemostoreApiSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://demostore.gatling.io")
        .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*", ".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*", ".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*", ".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
        .acceptHeader("application/json")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.9")
        .doNotTrackHeader("1")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");

    private Map<CharSequence, String> headers_0 = Map.of("sec-gpc", "1");

    private Map<CharSequence, String> headers_3 = Map.ofEntries(
        Map.entry("Content-Type", "application/json"),
        Map.entry("Origin", "http://demostore.gatling.io"),
        Map.entry("sec-gpc", "1")
    );

    private Map<CharSequence, String> headers_4 = Map.ofEntries(
        Map.entry("Content-Type", "application/json"),
        Map.entry("Origin", "http://demostore.gatling.io"),
        Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY3MjMyMTQxMCwiZXhwIjoxNjcyMzI1MDEwfQ.hWm2IJfRaW08IrLeeFdnzn6owiB7WsNlNElSqj05AFQ"),
        Map.entry("sec-gpc", "1")
    );


    private ScenarioBuilder scn = scenario("DemostoreApiSimulation")
        .exec(
            http("request_0")
                .get("/api/category")
                .headers(headers_0)
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0000_response.json")))
        )
        .pause(2)
        .exec(
            http("request_1")
                .get("/api/product?category=7")
                .headers(headers_0)
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0001_response.json")))
        )
        .pause(2)
        .exec(
            http("request_2")
                .get("/api/product/34")
                .headers(headers_0)
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0002_response.json")))
        )
        .pause(2)
        .exec(
            http("request_3")
                .post("/api/authenticate")
                .headers(headers_3)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0003_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0003_response.json")))
        )
        .pause(2)
        .exec(
            http("request_4")
                .put("/api/product/34")
                .headers(headers_4)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0004_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0004_response.json")))
        )
        .pause(2)
        .exec(
            http("request_5")
                .post("/api/product")
                .headers(headers_4)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0005_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0005_response.json")))
        )
        .pause(2)
        .exec(
            http("request_6")
                .put("/api/category/7")
                .headers(headers_4)
                .body(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0006_request.json"))
                .check(bodyBytes().is(RawFileBody("gatlingdemostoreapi/demostoreapisimulation/0006_response.json")))
        );

    {
        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }
}
