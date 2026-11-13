package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.AuthConfig;
import config.ConfigReader;
import config.ProjectConfiguration;
import config.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.*;
import testdata.TestData;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    MainPage mainPage = new MainPage();
    AppsPage appsPage = new AppsPage();
    SearchPage searchPage = new SearchPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    WishlistPage wishlistPage = new WishlistPage();
    TestData testData = new TestData();

    private static final WebDriverConfig config = ConfigReader.Instance.read();
    private static final AuthConfig authConfig = ConfigReader.Instance.readAuth();

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void beforeAll() {
        new ProjectConfiguration(config, authConfig).webConfig();
    }

    @AfterEach
    void addAttachments() {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            return;
        }
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
