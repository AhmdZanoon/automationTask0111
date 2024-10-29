package apiTests;

import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BreedsEndpointTest extends ApiTestSetup {


    @BeforeClass
    public void beforeClass() {
        baseURI = "https://api.thedogapi.com/v1/";
    }


    @Test()
    public void _01GetBreedsWithAuthentication() {
        given()
                .filter(new AllureRestAssured())
                .header("x-api-key", apiProperties.apiKey())
                .queryParam("limit", 5)
                .queryParam("page",0)
                .log().all().
                when().
                get("/breeds/").
                then().log().all()
                .statusCode(200)
                .body("image.url",everyItem(notNullValue()))
                .extract()
                .response();

    }


    @Test
    public void _02GetBreedsWithoutAuthentication() {
        given()
                .filter(new AllureRestAssured())
                .queryParam("limit", 2)
                .queryParam("page",0)
                .log().all().
                when().
                get("/breeds/").
                then().log().all()
                .statusCode(200)
                .body("size()",equalTo(2))
                .body("find { it.id == 1 }.name", equalTo("Affenpinscher"))
                .extract()
                .response();

    }


}
