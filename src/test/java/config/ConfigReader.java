package config;

import org.aeonbits.owner.ConfigFactory;

import java.util.Map;

public enum ConfigReader {
    Instance;

    private static final WebDriverConfig webDriverConfig = ConfigFactory.create(
            WebDriverConfig.class,
            System.getProperties(),
            Map.of("env", System.getProperty("env", "local"))
    );

    private static final AuthConfig authConfig = ConfigFactory.create(
            AuthConfig.class,
            System.getProperties()
    );

    public WebDriverConfig read() {
        return webDriverConfig;
    }

    public AuthConfig readAuth() {
        return authConfig;
    }
}
