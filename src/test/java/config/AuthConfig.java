package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "file:./auth.properties",
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();
}
