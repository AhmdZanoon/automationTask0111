package apiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Breeds Endpoint")
@Link("https://developers.thedogapi.com/")
public class BreedsEndpointTest extends ApiTestSetup {


    @Test(description = "TC0201 - Get Breeds With Api Key Provided And Assert That Image Url Return")
    public void _01GetBreedsWithAuthentication() {
        given()
                .filter(new AllureRestAssured())
                .header("x-api-key", apiProperties.apiKey())
                .queryParam("limit", 5)
                .queryParam("page", 0)
                .log().all().
                when().
                get("/breeds/").
                then().log().all()
                .statusCode(200)
                .body("image.url", everyItem(notNullValue()))
                .extract()
                .response();

    }

    @Test(description = "TC0202 - Get Breeds Without Api Key Provided And Assert On Size Of Response and The Name Of first Breed")
    public void _02GetBreedsWithoutAuthentication() {
        given()
                .filter(new AllureRestAssured())
                .queryParam("limit", 2)
                .queryParam("page", 0)
                .log().all().
                when().
                get("/breeds/").
                then().log().all()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("find { it.id == 1 }.name", equalTo("Affenpinscher"))
                .extract()
                .response();

    }


}
