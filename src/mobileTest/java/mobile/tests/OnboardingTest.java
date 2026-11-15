package mobile.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.JiraIssue;
import io.qameta.allure.JiraIssues;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Layer("mobile")
@Owner("aukolova")
@Feature("Первый запуск приложения")
public class OnboardingTest extends TestBase {

    @Test
    @Story("Экраны онбординга")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Первый запуск: прохождение без авторизации по кнопке \" Не сейчас \"")
    void onboardingWithoutAuthToMainScreenTest() {
        onboardingScreen.verifyPrimerScreenOpen();
        onboardingScreen.continueFromPrimer();
        onboardingScreen.allowNotifications();
        onboardingScreen.verifyTrackingScreenOpen();
        onboardingScreen.continueFromTracking();
        onboardingScreen.verifyAuthScreenOpen();
        onboardingScreen.skipAuth();
        bottomNavigation.verifyCatalogTabVisible();
    }

    @Test
    @Story("Экраны онбординга")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Первый запуск: переход к экрану авторизации по кнопке \"Авторизоваться\"")
    void onboardingProceedToLoginScreenTest() {
        onboardingScreen.verifyPrimerScreenOpen();
        onboardingScreen.continueFromPrimer();
        onboardingScreen.allowNotifications();
        onboardingScreen.verifyTrackingScreenOpen();
        onboardingScreen.continueFromTracking();
        onboardingScreen.verifyAuthScreenOpen();
        onboardingScreen.openLoginScreen();
        loginScreen.verifyLoginScreenOpen();
    }
}
