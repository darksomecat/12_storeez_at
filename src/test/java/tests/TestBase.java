package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.*;
import testdata.TestData;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    MainPage mainPage = new MainPage();
    MenuCollectionPage menuCollectionPage = new MenuCollectionPage();
    AppsPage appsPage = new AppsPage();
    SearchPage searchPage = new SearchPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    WishlistPage wishlistPage = new WishlistPage();
    TestData testData = new TestData();

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://12storeez.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.remote = System.getProperty("remote");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("pageLoadStrategy", "eager");
        capabilities.setCapability("se:cdpEnabled", false);
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true,
                "screenResolution", "1920x1080x24"
        ));

        if ("chrome".equalsIgnoreCase(Configuration.browser)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            chromeOptions.addArguments(List.of("--disable-dev-shm-usage", "--no-sandbox"));
            capabilities.merge(chromeOptions);
        } else if ("firefox".equalsIgnoreCase(Configuration.browser)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            firefoxOptions.setCapability("webSocketUrl", false);
            capabilities.merge(firefoxOptions);
        }

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAtachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
