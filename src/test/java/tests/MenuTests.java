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
@Feature("Боковая панель каталога товаров")
public class MenuTests extends TestBase {

    @Test
    @Story("Реализация sidebar с каталогом товаров")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
    @DisplayName("Sidebar: вкладка Женщинам и список ссылок")
    void leftMenuWomenCollectionTest() {
        mainPage.openPage()
                .openLeftMenu()
                .checkMenuOpened()
                .selectMenuGender(testData.womenTab)
                .checkActiveMenuGender(testData.womenTab);
        menuCollectionPage.checkCollectionLinks(testData.womenCollectionLinks);
        mainPage.closeLeftMenu()
                .checkMenuClosed();
    }

    @Test
    @Story("Реализация sidebar с каталогом товаров")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
    @DisplayName("Sidebar: вкладка Мужчинам и список ссылок")
    void leftMenuManCollectionTest() {
        mainPage.openPage()
                .openLeftMenu()
                .checkMenuOpened()
                .selectMenuGender(testData.menTab)
                .checkActiveMenuGender(testData.menTab);
        menuCollectionPage.checkCollectionLinks(testData.menCollectionLinks);
        mainPage.closeLeftMenu()
                .checkMenuClosed();
    }
}
