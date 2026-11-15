package mobile.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.JiraIssue;
import io.qameta.allure.JiraIssues;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Layer("mobile")
@Owner("aukolova")
@Feature("Wishlist")
public class WishlistTests extends TestBase {

    @BeforeEach
    void skipOnboarding() {
        completeOnboarding();
    }

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление и проверка товара в вкладке \"Список избранного\"")
    void addProductToWishlistTest() {
        bottomNavigation.openCatalog();
        catalogScreen.openNovelties();
        catalogScreen.verifyProductListLoaded();
        String product = catalogScreen.getFirstProductTitle();
        catalogScreen.openFirstProduct();
        productScreen.verifyProductScreenOpen();
        productScreen.addToWishlist();
        productScreen.navigateBack();
        bottomNavigation.openWishlist();
        wishlistScreen.verifyProductInWishlist(product);
    }

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление товара из \"Списка избранного\"")
    void removeProductFromWishlistTest() {
        bottomNavigation.openCatalog();
        catalogScreen.openNovelties();
        catalogScreen.verifyProductListLoaded();
        String product = catalogScreen.getFirstProductTitle();
        catalogScreen.openFirstProduct();
        productScreen.verifyProductScreenOpen();
        productScreen.addToWishlist();
        productScreen.navigateBack();
        bottomNavigation.openWishlist();
        wishlistScreen.verifyProductInWishlist(product);
        wishlistScreen.removeProduct();
        wishlistScreen.verifyWishlistIsEmpty();
    }
}
