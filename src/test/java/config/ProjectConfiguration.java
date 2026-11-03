package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.Map;

public class ProjectConfiguration {
    private final WebDriverConfig config;
    private final AuthConfig authConfig;

    public ProjectConfiguration(WebDriverConfig config, AuthConfig authConfig) {
        this.config = config;
        this.authConfig = authConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser().name().toLowerCase();
        if (config.browserVersion() != null && !config.browserVersion().isBlank()) {
            Configuration.browserVersion = config.browserVersion();
        }
        Configuration.browserSize = config.browserSize();
        Configuration.headless = config.headless();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("pageLoadStrategy", "eager");
        capabilities.setCapability("se:cdpEnabled", false);

        if (config.isRemote()) {
            Configuration.remote = String.format(
                    config.remote(),
                    authConfig.username(),
                    authConfig.password()
            );
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "screenResolution", "1920x1080x24"
            ));
        }

        if (config.browser() == Browser.CHROME) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            chromeOptions.addArguments(List.of("--disable-dev-shm-usage", "--no-sandbox"));
            capabilities.merge(chromeOptions);
        } else if (config.browser() == Browser.FIREFOX) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            firefoxOptions.setCapability("webSocketUrl", false);
            capabilities.merge(firefoxOptions);
        }

        Configuration.browserCapabilities = capabilities;
    }
}
