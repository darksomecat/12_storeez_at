package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class MenuTests extends TestBase {

    @Feature("Левое меню")
    @Test
    @DisplayName("Левое меню: вкладка Женщинам и список ссылок")
    void leftMenuWomenCollectionTest() {
        step("Открыть главную страницу", () ->
                mainPage.openPage());
        step("Открыть левое меню", () ->
                mainPage.openLeftMenu());
        step("Проверить, что левое меню открыто", () ->
                mainPage.checkMenuOpened());
        step("Выбрать вкладку Женщинам", () ->
                mainPage.selectMenuGender(testData.womenTab));
        step("Проверить активную вкладку Женщинам", () ->
                mainPage.checkActiveMenuGender(testData.womenTab));
        step("Проверить ссылки коллекции Женщинам", () ->
                menuCollectionPage.womenCollection());
        step("Закрыть левое меню", () ->
                mainPage.closeLeftMenu());
        step("Проверить, что левое меню закрыто", () ->
                mainPage.checkMenuClosed());
    }

    @Feature("Левое меню")
    @Test
    @DisplayName("Левое меню: вкладка Мужчинам и список ссылок")
    void leftMenuManCollectionTest() {
        step("Открыть главную страницу", () ->
                mainPage.openPage());
        step("Открыть левое меню", () ->
                mainPage.openLeftMenu());
        step("Проверить, что левое меню открыто", () ->
                mainPage.checkMenuOpened());
        step("Выбрать вкладку Мужчинам", () ->
                mainPage.selectMenuGender(testData.menTab));
        step("Проверить активную вкладку Мужчинам", () ->
                mainPage.checkActiveMenuGender(testData.menTab));
        step("Проверить ссылки коллекции Мужчинам", () ->
                menuCollectionPage.manCollection());
        step("Закрыть левое меню", () ->
                mainPage.closeLeftMenu());
        step("Проверить, что левое меню закрыто", () ->
                mainPage.checkMenuClosed());
    }
}
