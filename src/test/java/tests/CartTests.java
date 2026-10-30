package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class CartTests extends TestBase {

    @Feature("Корзина")
    @Test
    @DisplayName("Добавление товара в корзину и проверка в корзине")
    void addProductToCartTest() {
        step("Открыть главную страницу", () ->
                mainPage.openPage());
        step("Найти товар по запросу «" + testData.searchWord + "»", () -> {
            mainPage.openSearch();
            searchPage.typeQuery(testData.searchWord)
                    .submitSearch();
        });
        step("Открыть первую карточку товара по запросу «" + testData.searchWord + "»", () -> {
            searchPage.openFirstProductCard();
            productPage.checkOpened()
                    .checkTitleContains(testData.searchWord);
        });
        step("Выбрать первый доступный размер", () ->
                productPage.selectSize());
        step("Добавить товар в корзину", () ->
                productPage.addToCart());
        step("Открыть корзину", () ->
                productPage.openCart());
        step("Проверить товар «" + testData.searchWord + "» в корзине", () ->
                cartPage.checkProductPresent(testData.searchWord));
    }

    @Feature("Корзина")
    @Test
    @DisplayName("Удаление товара из корзины")
    void removeProductFromCartTest() {
        step("Добавить товар в корзину по запросу «" + testData.searchWord + "»", () -> {
            mainPage.openPage().openSearch();
            searchPage.typeQuery(testData.searchWord)
                    .submitSearch()
                    .openFirstProductCard();
            productPage.checkOpened()
                    .selectSize()
                    .addToCart();
        });
        step("Открыть корзину", () ->
                productPage.openCart());
        step("Проверить товар «" + testData.searchWord + "» в корзине", () ->
                cartPage.checkProductPresent(testData.searchWord));
        step("Удалить товар из корзины", () ->
                cartPage.removeProduct());
        step("Проверить, что корзина пустая", () ->
                cartPage.checkEmpty(testData.emptyCartText));
    }

    @Feature("Корзина")
    @Test
    @DisplayName("Пустая корзина")
    void emptyCartTest() {
        step("Открыть главную страницу", () ->
                mainPage.openPage());
        step("Открыть корзину", () ->
                mainPage.openCartFromHeader());
        step("Проверить текст пустой корзины", () ->
                cartPage.checkEmpty(testData.emptyCartText));
    }
}