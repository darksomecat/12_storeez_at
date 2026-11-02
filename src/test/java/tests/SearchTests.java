package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchTests extends TestBase {

    @Feature("Поиск")
    @Test
    @DisplayName("Поиск при вводе значения: в карточках есть соответствующие результаты")
    void searchByWordTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .checkResultHeadline(testData.searchResultHeadline, testData.searchWord)
                .checkProductCardsContain(testData.searchWord);
    }

    @Feature("Поиск")
    @Test
    @DisplayName("Поиск через первую подсказку")
    void searchBySuggestionTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.checkSearchTapsVisible()
                .clickFirstSearchTap()
                .submitSearch()
                .checkResultsMatchSelectedTap(testData.searchResultHeadline, searchPage.getSelectedSearchTap());
    }
}
