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
@Feature("Корзина")
public class CartTests extends TestBase {

    @BeforeEach
    void skipOnboarding() {
        completeOnboarding();
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("blocker")})
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Добавление товара в корзину и проверка товара в корзине")
    void addProductToCartTest() {
        bottomNavigation.openCatalog();
        catalogScreen.openNovelties();
        catalogScreen.verifyProductListLoaded();
        String product = catalogScreen.getFirstProductTitle();
        catalogScreen.openFirstProduct();
        productScreen.verifyProductScreenOpen();
        productScreen.addToCart();
        productScreen.selectFirstAvailableSize();
        productScreen.verifyAddedToCart();
        productScreen.goToCart();
        cartScreen.verifyCartScreenOpen();
        cartScreen.verifyProductInCart(product);
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление товара из корзины")
    void clearCartTest() {
        bottomNavigation.openCatalog();
        catalogScreen.openNovelties();
        catalogScreen.verifyProductListLoaded();
        catalogScreen.getFirstProductTitle();
        catalogScreen.openFirstProduct();
        productScreen.verifyProductScreenOpen();
        productScreen.addToCart();
        productScreen.selectFirstAvailableSize();
        productScreen.verifyAddedToCart();
        productScreen.goToCart();
        cartScreen.verifyCartScreenOpen();
        cartScreen.startClearing();
        cartScreen.confirmClearCart();
        cartScreen.verifyCartIsEmpty();
    }

    @Test
    @Story("Пустая корзина")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("minor")})
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Пустая корзина")
    void emptyCartTest() {
        bottomNavigation.openCart();
        cartScreen.verifyCartIsEmpty();
    }
}
