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
@Feature("Wishlist")
public class WishlistTests extends TestBase {

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("critical")})
    @DisplayName("Добавление в \"Список избранного\" для неавторизованного пользователя")
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
