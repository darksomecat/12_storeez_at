package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class WishlistTests extends TestBase {

    @Feature("Вишлист")
    @Test
    @DisplayName("Добавление в вишлист для неавторизованного пользователя")
    void addToWishlistUnauthorizedTest() {
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
        step("Добавить товар в вишлист", () ->
                productPage.addToWishlist());
        step("Проверить попап логина вишлиста для неавторизованного пользователя", () ->
                productPage.checkWishlistLoginPopup());
        step("Закрыть попап логина вишлиста", () ->
                productPage.closeWishlistLoginPopup());
        step("Открыть вишлист из шапки", () ->
                mainPage.openWishlistFromHeader());
        step("Проверить товар «" + testData.searchWord + "» в вишлисте", () ->
                wishlistPage.checkProductPresent(testData.searchWord));
    }
}