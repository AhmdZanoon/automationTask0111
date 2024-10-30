package apiTests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API Tests")
@Feature("Images Endpoint")
@Link("https://developers.thedogapi.com/")
public class ImagesEndpointTest extends ApiTestSetup {
    String uploadedImageId;
    String imageUrl ;
    int width;
    int height;


    @Test (description = "TC0101 - Test Post Method By Uploading New Image And asserting Status Code and Returned url")
    public void _01UploadImage() {
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

    @Test (description = "TC0102 - Testing Get Method By Calling The Uploaded Image In TC0101 and asserting on Response Body")
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

    @Test(description = "TC0103 - Testing Delete Method By Deleting uploaded Image in TC0101 and asserting on Status Code")
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

    @Test (description = "TC0104 - Testing Get Method By Calling The Uploaded Image In TC0101 after Deleting and Asserting on Response Body")
    public void _04GetUploadedImageAfterDelete() {
        given()
                .filter(new AllureRestAssured())
                .header("x-api-key", apiProperties.apiKey())
                .log().all().
                when().
                get("/images/"+this.uploadedImageId).
                then().log().all()
                .statusCode(400)
                .assertThat()
                .body(containsString("Couldn't find an image matching the passed 'id' of " +this.uploadedImageId))
                .extract()
                .response();

    }
}
