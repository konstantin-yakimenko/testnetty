package langdating

import io.gatling.core.Predef._     // required for Gatling core structure DSL
// import io.gatling.jdbc.Predef._     // can be omitted if you don't use jdbcFeeder
import io.gatling.http.Predef._     // required for Gatling HTTP DSL

import scala.concurrent.duration._  // used for specifying duration unit, eg "5 second"

class TestSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080") // Here is the root for all relative URLs

  val headers = Map("Content-Type" -> "application/json")
//  val headers_auth = Map("Content-Type" -> "application/json", "Authorization" -> "Bearer ${token}")

  object PostCity {
    val execute = exec(http("post_city")
      .post("/api/city")
      .body(StringBody("{\"id\":1,\"title\":\"Moscow\"}"))
      .check(jsonPath("$.id").saveAs("cityId"))
    )
  }

  object GetCity {
    val execute = exec(http("get_city")
      .get("/api/city/${cityId}")
    )
  }

  object CitySet {
    val execute = exec(http("set_city")
      .put("/api/city/${cityId}")
      .body(StringBody("{\"title\":\"Krasnodar\"}"))
    )
  }

  object CityDelete {
    val execute = exec(http("city_delete")
      .delete("/api/city/${cityId}")
    )
  }

  val scn = scenario("Base scenario") // A scenario is a chain of requests and pauses
      .exec(PostCity.execute)
      .exec(GetCity.execute)
      .exec(CitySet.execute)
      .exec(GetCity.execute)
      .exec(CityDelete.execute)

  setUp(
    scn.inject(
      constantConcurrentUsers(10) during (10 seconds),
      rampConcurrentUsers(10) to (100) during (60 seconds)
    )
      .protocols(httpProtocol)
  )
//  setUp(scn.inject(
//    incrementUsersPerSec(5) // Double
//      .times(5)
//      .eachLevelLasting(10 seconds)
//      .separatedByRampsLasting(10 seconds)
//      .startingFrom(10))
//    .protocols(httpProtocol))

}

//https://gatling.io/docs/current/http/http_check/

//    .exec(http("post_lanny")
//      .post("/person")
//      .headers(headers)
//      .body(StringBody("{\"personId\":1,\"name\":\"Lanny\",\"sex\":\"m\",\"birth\":\"1994-03-24\",\"countryId\":1,\"cityId\":1,\"status\":\"look for boyfriend\",\"email\":\"lanny@gmail.com\",\"approveEmail\":\"2019-03-24 18:08:56+0300\",\"phone\":\"89610007733\",\"approvePhone\":\"2019-03-24 17:55:56+0300\",\"createDate\":null,\"premiumStart\":null,\"premiumFinish\":null,\"about\":\"I am very interesting person\",\"interests\":[\"sex\",\"box\",\"jazz\"],\"version\":0}"))
//    )
//    .exec(http("get_marry")
//      .get("/person/2")
//      .headers(headers)
//    )
//    .exec(http("get_lanny")
//      .get("/person/1")
//      .headers(headers)
//    )
//    .exec(http("post_marry")
//      .post("/person")
//      .headers(headers)
//      .body(StringBody("{\"personId\":2,\"name\":\"Marrya\",\"sex\":\"f\",\"birth\":\"1994-03-24\",\"countryId\":1,\"cityId\":1,\"status\":\"look for boyfriend\",\"email\":\"lanny@gmail.cor\",\"approveEmail\":\"2019-03-24 18:08:56+0300\",\"phone\":\"89610007731\",\"approvePhone\":\"2019-03-24 17:55:56+0300\",\"createDate\":null,\"premiumStart\":null,\"premiumFinish\":null,\"about\":\"I am very interesting person\",\"interests\":[\"sex\",\"box\",\"jazz\"],\"version\":0}"))
//    )
//    .exec(http("get_lanny")
//      .get("/person/1")
//      .headers(headers)
//    )
//    .exec(http("get_marry")
//    .get("/person/2")
//    .headers(headers)
//    )

//  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
