package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTests extends TestBase {

    @Feature("Левое меню")
    @Test
    @DisplayName("Левое меню: вкладка Женщинам и список ссылок")
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

    @Feature("Левое меню")
    @Test
    @DisplayName("Левое меню: вкладка Мужчинам и список ссылок")
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
