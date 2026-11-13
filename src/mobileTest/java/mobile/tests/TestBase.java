package mobile.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import mobile.drivers.DriverFactory;
import mobile.helpers.Attach;
import mobile.steps.OnboardingSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    protected final OnboardingSteps onboardingSteps = new OnboardingSteps();

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
        onboardingSteps.recoverFromStartupErrorIfShown();
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
