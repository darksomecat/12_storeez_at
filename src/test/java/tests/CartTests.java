package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTests extends TestBase {

    @Feature("Корзина")
    @Test
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

    @Feature("Корзина")
    @Test
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

    @Feature("Корзина")
    @Test
    @DisplayName("Пустая корзина")
    void emptyCartTest() {
        mainPage.openPage()
                .openCartFromHeader();
        cartPage.checkEmpty(testData.emptyCartText);
    }
}
