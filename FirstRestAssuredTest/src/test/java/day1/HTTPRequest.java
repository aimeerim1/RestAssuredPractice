package day1;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest
        .Matchers.*;
import java.util.HashMap;

public class HTTPRequest {

    // 👇 глобальная переменная для хранения id
    static String id;
       @Test
  void getUser() {
       RestAssured
                .given()
                 .header("x-api-key", "reqres-free-v1")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }
    @Test(priority = 2)
    public void createUser() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "pavan");
        data.put("job", "trainer");

        // сохраняем id из JSON-ответа
        id = given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all()
                .extract()
                .path("id"); // 👈 сохраняем id
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "john");
        data.put("job", "teacher");

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200) // ✅ статус 200 для update
                .log().all();
    }
    @Test(priority = 4, dependsOnMethods = {"createUser"})
    public void deleteUser() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204) // 204 No Content — стандартный ответ при успешном удалении
                .log().all();
    }

}
