package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.JiraIssue;
import io.qameta.allure.JiraIssues;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Layer("web")
@Owner("aukolova")
@Feature("Экран «Установите приложение»")
public class AppsPageTests extends TestBase {

    @Test
    @Story("Переход на экран установки мобильного приложения и проверка формы")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("minor")})
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