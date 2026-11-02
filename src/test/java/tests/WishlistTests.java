package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WishlistTests extends TestBase {

    @Feature("Вишлист")
    @Test
    @DisplayName("Добавление в вишлист для неавторизованного пользователя")
    void addToWishlistUnauthorizedTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened()
                .checkTitleContains(testData.searchWord)
                .addToWishlist()
                .checkWishlistLoginPopup(
                        testData.wishlistLoginTitle,
                        testData.wishlistLoginText,
                        testData.wishlistLoginNext
                )
                .closeWishlistLoginPopup(testData.wishlistLoginClose);
        mainPage.openWishlistFromHeader();
        wishlistPage.checkProductPresent(testData.wishlistPageTitle, testData.searchWord);
    }
}
