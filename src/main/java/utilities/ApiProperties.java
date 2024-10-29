package utilities;

import org.aeonbits.owner.Config;

import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Sources({
        "system:properties",
        "system:env",
        "file:src/main/resources/properties/ApiProperties.properties",
        "classpath:ApiProperties.properties",

})


public interface ApiProperties extends Config {

    @Key("url")
    String baseUri();




    @Key("apiKey")
    @DefaultValue("")
    String apiKey();


}