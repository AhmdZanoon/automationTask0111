package apiTests;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import utilities.ApiProperties;

import static io.restassured.RestAssured.baseURI;

public class ApiTestSetup {

    protected static final ApiProperties apiProperties = ConfigFactory.create(ApiProperties.class);
    @BeforeClass(description = "Test Setup")
    public void beforeClass() {
        baseURI = "https://api.thedogapi.com/v1/";
    }


}
