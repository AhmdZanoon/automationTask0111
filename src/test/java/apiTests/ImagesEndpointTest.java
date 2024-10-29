package apiTests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static apiTests.ApiTestSetup.apiProperties;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ImagesEndpointTest {
    String uploadedImageId;
    String imageUrl ;
    int width;
    int height;


    @BeforeClass
    public void beforeClass() {
        baseURI = "https://api.thedogapi.com/v1/";

    }
    @Test
    public void _01uploadImage() {
        String imagePath = "src/test/resources/testDataFile/apiTestData.jpg";
        File file = new File(imagePath);
        Response response = given()
                .filter(new AllureRestAssured())
                .header("x-api-key", apiProperties.apiKey())
                .multiPart("file", file, "image/jpeg")
                .log().all()
                .when()
                .post("images/upload")
                .then()
                .log().all()
                .statusCode(201)
                .body("url", notNullValue())
                .extract()
                .response();

         this.imageUrl = response.jsonPath().getString("url");
         this.uploadedImageId = response.jsonPath().getString("id");
         this.width = response.jsonPath().getInt("width");
         this.height = response.jsonPath().getInt("height");


    }

    @Test
    public void _02getUploadedImage() {
        given()
                .filter(new AllureRestAssured())
                .header("x-api-key", apiProperties.apiKey())
                .log().all().
                when().
                get("/images/"+this.uploadedImageId).
                then().log().all()
                .statusCode(200)
                .assertThat()
                .body("url", equalTo(this.imageUrl),
                        "width", equalTo(this.width),
                        "height", equalTo((this.height)))
                .extract()
                .response();

    }

    @Test
    public void _03deleteUploadedImage() {
        given()
                .filter(new AllureRestAssured())
                .header("x-api-key", apiProperties.apiKey())
                .log().all().
                when().
                delete("/images/"+this.uploadedImageId).
                then().log().all()
                .statusCode(204)
                .assertThat().body(equalTo(""))
                .extract()
                .response();

    }
}
