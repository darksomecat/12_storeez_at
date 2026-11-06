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
@Feature("Корзина")
public class CartTests extends TestBase {

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("blocker")})
    @DisplayName("Добавление товара в корзину и проверка в корзине")
    void addProductToCartTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened()
                .checkTitleContains(testData.searchWord)
                .selectSize(testData.sizeExcludeText)
                .addToCart(testData.addToCartButton, testData.goToCartButton)
                .openCart(testData.goToCartButton);
        cartPage.checkProductPresent(testData.searchWord);
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("critical")})
    @DisplayName("Удаление товара из корзины")
    void removeProductFromCartTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened()
                .selectSize(testData.sizeExcludeText)
                .addToCart(testData.addToCartButton, testData.goToCartButton)
                .openCart(testData.goToCartButton);
        cartPage.checkProductPresent(testData.searchWord)
                .removeProduct(testData.removeFromCartConfirm)
                .checkEmpty(testData.emptyCartText);
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("minor")})
    @DisplayName("Пустая корзина")
    void emptyCartTest() {
        mainPage.openPage()
                .openCartFromHeader();
        cartPage.checkEmpty(testData.emptyCartText);
    }
}
