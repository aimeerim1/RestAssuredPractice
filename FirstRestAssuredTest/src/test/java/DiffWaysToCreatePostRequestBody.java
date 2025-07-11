import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest
        .Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONObject;


public class DiffWaysToCreatePostRequestBody {
    //using HashMap
    //@Test(priority = 1)
    void testPostUsingHashMap(){
        HashMap data = new HashMap();
        data.put("name","Aimeerim");
        data.put("job","programmer");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("Aimeerim"))
                .body("job",equalTo("programmer"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }
    //using org.json
//    @Test(priority = 1)
    void testPostUsingJsonLibrary(){
        JSONObject data = new JSONObject();
        data.put("name","Aimeerim");
        data.put("job","programmer");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("Aimeerim"))
                .body("job",equalTo("programmer"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }
    //using POJO
//    @Test(priority = 1)
    void testPostUsingPOJO(){
        POJO_Request data = new POJO_Request();
        data.setName("Aimeerim");
        data.setJob("teacher");

        given()
            .header("x-api-key", "reqres-free-v1")
            .contentType("application/json")
            .body(data)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .body("name",equalTo("Aimeerim"))
            .body("job",equalTo("teacher"))
            .header("Content-Type","application/json; charset=utf-8")
            .log().all();
    }
//using external json file
    @Test(priority = 1)
    void testPostUsingJsonFile() throws FileNotFoundException {
        File f = new File("src/test/resources/body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data =  new JSONObject(jt);

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("Aimeerim"))
                .body("job",equalTo("teacher"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
    }
    @Test(priority = 2)
    public void deleteUser() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/" + 434)
                .then()
                .statusCode(204) // 204 No Content — стандартный ответ при успешном удалении
                .log().all();
    }
}
