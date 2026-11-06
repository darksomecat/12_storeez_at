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
@Feature("Поиск по товарам")
public class SearchTests extends TestBase {

    @Test
    @Story("Панель быстрого поиска")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("critical")})
    @DisplayName("Поиск при вводе значения: в карточках есть соответствующие результаты")
    void searchByWordTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .checkResultHeadline(testData.searchResultHeadline, testData.searchWord)
                .checkProductCardsContain(testData.searchWord);
    }

    @Test
    @Story("Панель быстрого поиска")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
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
