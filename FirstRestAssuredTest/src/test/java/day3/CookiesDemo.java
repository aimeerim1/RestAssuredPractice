package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
public class CookiesDemo {
//    @Test(priority = 1)
    void testCookies(){
        given()
        .when()
            .get("https://google.com/")
        .then()
            .cookie("AEC","kdsf")
            .log().all();
    }
    @Test(priority = 2)
    void getCookiesInfo(){
       Response res=  given()
                .when()
                .get("https://google.com/");
       //=================================get single cookie info==========================
       // String cookies_value=res.getCookie("AEC");
       // System.out.println("Cookie value is "+cookies_value);

        //================================get all cookies info============================
        Map<String, String> cookies = res.getCookies();
        System.out.println("Cookies: " + cookies.keySet());
    }
}
