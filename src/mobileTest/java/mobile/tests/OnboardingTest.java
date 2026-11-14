package mobile.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("aukolova")
@Feature("Первый запуск приложения")
public class OnboardingTest extends TestBase {

    @Test
    @Story("Экраны онбординга")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Первый запуск: прохождение без авторизации по кнопке \" Не сейчас \"")
    void onboardingWithoutAuthToMainScreenTest() {
        onboardingSteps.checkNotificationPrimerScreen()
                .continueFromNotificationPrimer()
                .allowNotifications()
                .checkTrackingScreen()
                .continueFromTracking()
                .checkAuthScreen()
                .skipAuth()
                .checkMainScreenOpened();
    }

    @Test
    @Story("Экраны онбординга")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Первый запуск: переход к экрану авторизации по кнопке \"Авторизоваться\"")
    void onboardingProceedToLoginScreenTest() {
        onboardingSteps.checkNotificationPrimerScreen()
                .continueFromNotificationPrimer()
                .allowNotifications()
                .checkTrackingScreen()
                .continueFromTracking()
                .checkAuthScreen()
                .openLoginScreen()
                .checkLoginScreen();
    }
}
