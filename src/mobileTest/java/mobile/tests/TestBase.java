package mobile.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import mobile.drivers.DriverFactory;
import mobile.helpers.Attach;
import mobile.screens.BottomNavigation;
import mobile.screens.CartScreen;
import mobile.screens.CatalogScreen;
import mobile.screens.LoginScreen;
import mobile.screens.OnboardingScreen;
import mobile.screens.ProductScreen;
import mobile.screens.WishlistScreen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    protected final OnboardingScreen onboardingScreen = new OnboardingScreen();
    protected final LoginScreen loginScreen = new LoginScreen();
    protected final CatalogScreen catalogScreen = new CatalogScreen();
    protected final ProductScreen productScreen = new ProductScreen();
    protected final CartScreen cartScreen = new CartScreen();
    protected final WishlistScreen wishlistScreen = new WishlistScreen();
    protected final BottomNavigation bottomNavigation = new BottomNavigation();

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = DriverFactory.androidDriver().getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = -1;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
        onboardingScreen.waitForAppStart();
    }

    protected void completeOnboarding() {
        onboardingScreen.verifyPrimerScreenOpen();
        onboardingScreen.continueFromPrimer();
        onboardingScreen.allowNotifications();
        onboardingScreen.verifyTrackingScreenOpen();
        onboardingScreen.continueFromTracking();
        onboardingScreen.verifyAuthScreenOpen();
        onboardingScreen.skipAuth();
        bottomNavigation.waitForCatalogTab();
    }

    @AfterEach
    void addAttachments() {
        try {
            Attach.safely("Screenshot", () -> Attach.screenshotAs("Last screenshot"));
            Attach.safely("Page source", Attach::pageSource);
        } finally {
            closeWebDriver();
        }
    }
}
