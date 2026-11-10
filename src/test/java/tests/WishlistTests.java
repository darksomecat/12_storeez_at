package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.JiraIssue;
import io.qameta.allure.JiraIssues;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление в \"Список избранного\" для неавторизованного пользователя")
    void addToWishlistUnauthorizedTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened(testData.searchWord)
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

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Пустая страница вишлиста")
    void emptyWishlistTest() {
        mainPage.openPage()
                .openWishlistFromHeader();
        wishlistPage.checkEmpty(
                testData.wishlistPageTitle,
                testData.wishlistEmptyTitle,
                testData.wishlistEmptyDescription,
                testData.wishlistCatalogButton
        );
    }

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление товара из вишлиста")
    void removeProductFromWishlistTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened(testData.searchWord)
                .addToWishlist()
                .closeWishlistLoginPopup(testData.wishlistLoginClose);
        mainPage.openWishlistFromHeader();
        wishlistPage.checkProductPresent(testData.wishlistPageTitle, testData.searchWord)
                .checkProductWishlistActive(testData.searchWord)
                .clickProductWishlistButton()
                .checkProductWishlistInactive(testData.searchWord)
                .refreshPage()
                .checkEmpty(
                        testData.wishlistPageTitle,
                        testData.wishlistEmptyTitle,
                        testData.wishlistEmptyDescription,
                        testData.wishlistCatalogButton
                );
    }

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление товара в корзину из вишлиста")
    void addProductToCartFromWishlistTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened(testData.searchWord)
                .addToWishlist()
                .closeWishlistLoginPopup(testData.wishlistLoginClose);
        mainPage.openWishlistFromHeader();
        String cardTitle = wishlistPage.getProductCardTitle();
        wishlistPage.clickAddToCartButton(testData.wishlistAddToCartButton)
                .checkSizePopupOpened(testData.sizePopupTitle)
                .selectRandomSize(testData.sizeExcludeText);
        mainPage.openCartFromHeader();
        cartPage.checkAddedProduct(
                cardTitle,
                wishlistPage.getSelectedSize(),
                testData.cartItemQuantity
        );
    }

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Закрытие поп-апа выбора размера по крестику")
    void closeSizePopupFromWishlistTest() {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(testData.searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened(testData.searchWord)
                .addToWishlist()
                .closeWishlistLoginPopup(testData.wishlistLoginClose);
        mainPage.openWishlistFromHeader();
        wishlistPage.clickAddToCartButton(testData.wishlistAddToCartButton)
                .checkSizePopupOpened(testData.sizePopupTitle)
                .closeSizePopup()
                .checkProductPresent(testData.wishlistPageTitle, testData.searchWord);
    }
}
