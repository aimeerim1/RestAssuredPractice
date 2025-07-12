package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest
        .Matchers.*;
public class PathAndQueryParameters {
//https://reqres.in/api/users?page=2&id=5
    @Test
    void testQueryAndPath(){
    given()
            .header("x-api-key", "reqres-free-v1")
            .pathParam("mypath","users") //path parameter
            .queryParam("page",2)
            .queryParam("id",5)
    .when()
            .get("https://reqres.in/api/{mypath}")
    .then()
            .statusCode(200)
            .log()
            .all();
    }
}
