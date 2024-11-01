package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Sources({
        "system:properties",
        "system:env",
        "file:src/main/resources/properties/webProperties.properties",
        "classpath:WebProperties.properties",

})


public interface WebProperties extends Config {

    @Key("browser")
    String browser();

    @Key("webUrl")
    String webUrl();


    @Key("headlessMode")
    @DefaultValue("false")
    boolean headlessMode();



}