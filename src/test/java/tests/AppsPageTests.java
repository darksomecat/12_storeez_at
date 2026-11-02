package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppsPageTests extends TestBase {

    @Feature("Приложение")
    @Test
    @DisplayName("Переход на экран «Установите приложение» и проверка формы")
    void installAppPageTest() {
        mainPage.openPage()
                .openAppsFromMenu();
        appsPage.checkTitle(testData.appsPageTitle)
                .checkInstallBlockContent(
                        testData.appsBreadcrumb,
                        testData.appsQrHint,
                        testData.appsBenefitEarlyAccess,
                        testData.appsBenefitNotifications,
                        testData.appsBenefitQr
                );
    }
}