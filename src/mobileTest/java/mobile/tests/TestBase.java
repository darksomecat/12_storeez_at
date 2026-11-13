package mobile.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
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
        String sessionId = null;
        try {
            sessionId = Selenide.sessionId().toString();
            Attach.safely("Screenshot", () -> Attach.screenshotAs("Last screenshot"));
            Attach.safely("Page source", Attach::pageSource);
        } finally {
            closeWebDriver();
        }

        if (sessionId != null && DriverFactory.isBrowserstack()) {
            String id = sessionId;
            Attach.safely("Video", () -> Attach.addVideo(id));
            Attach.safely("Text logs", () -> Attach.textLogs(id));
            Attach.safely("Appium logs", () -> Attach.appiumLogs(id));
            Attach.safely("Device logs", () -> Attach.deviceLogs(id));
        }
    }
}
