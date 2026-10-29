package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class AppsPageTests extends TestBase {

    @Feature("Приложение")
    @Test
    @DisplayName("Переход на экран «Установите приложение» и проверка содержимого")
    void installAppPageTest() {
        step("Открыть главную страницу", () ->
                mainPage.openPage());
        step("Открыть пункт «Приложение 12 STOREEZ» в меню", () ->
                mainPage.openAppsFromMenu());
        step("Проверить заголовок страницы приложения", () ->
                appsPage.checkTitle(testData.appsPageTitle));
        step("Проверить содержимое блока установки приложения", () ->
                appsPage.checkInstallBlockContent());
    }
}
