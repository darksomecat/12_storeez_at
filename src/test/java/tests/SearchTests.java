package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Feature("Поиск")
    @Test
    @DisplayName("Поиск при вводе значения: в карточках есть соответствующие результаты")
    void searchByWordTest() {
        step("Открыть главную страницу", () ->
                mainPage.openPage());
        step("Открыть поиск", () ->
                mainPage.openSearch());
        step("Ввести поисковый запрос «" + testData.searchWord + "»", () ->
                searchPage.typeQuery(testData.searchWord));
        step("Отправить поиск Enter", () ->
                searchPage.submitSearch());
        step("Проверить заголовок результата по запросу «" + testData.searchWord + "»", () ->
                searchPage.checkResultHeadline(testData.searchWord));
        step("Проверить карточки товаров по запросу «" + testData.searchWord + "»", () ->
                searchPage.checkProductCardsContain(testData.searchWord));
    }

    @Feature("Поиск")
    @Test
    @DisplayName("Поиск через первую подсказку")
    void searchBySuggestionTest() {
        step("Открыть главную страницу", () ->
                mainPage.openPage());
        step("Открыть поиск", () ->
                mainPage.openSearch());
        step("Дождаться подсказок поиска", () ->
                searchPage.checkSearchTapsVisible());
        step("Кликнуть первую подсказку поиска", () ->
                searchPage.clickFirstSearchTap());
        step("Отправить поиск Enter", () ->
                searchPage.submitSearch());
        step("Проверить результаты по подсказке «" + searchPage.getSelectedSearchTap() + "»", () ->
                searchPage.checkResultsMatchSelectedTap());
    }
}