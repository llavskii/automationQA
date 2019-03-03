package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static tests.URLconst.*;

public class apiRestYandexTest {

    @Test
    public void getLangMethodTest() {
        given()
                .queryParam("key", API_KEY)
                .get(GET_LANG_API_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void lookupMethodTest() {
        Response response = given()
                .queryParam("key", API_KEY)
                .queryParam("lang", "ru-en")
                .queryParam("text", "жара")
                .get(LOOKUP_API_URL);
        JsonPath jsonPath = response.then().extract().body().jsonPath();
        response
                .then()
                .statusCode(200)
                .body("def[0].tr[0].text", equalTo("heat"))
                .body("def[0].tr.text", hasItem("heat"))
        ;
        System.out.println(jsonPath.getString("def[0].tr.text"));
    }
}
