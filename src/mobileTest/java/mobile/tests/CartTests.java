package mobile.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.JiraIssue;
import io.qameta.allure.JiraIssues;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mobile.steps.CartSteps;
import mobile.steps.CatalogSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Layer("mobile")
@Owner("aukolova")
@Feature("Корзина")
public class CartTests extends TestBase {

    private final CatalogSteps catalogSteps = new CatalogSteps();
    private final CartSteps cartSteps = new CartSteps();

    @BeforeEach
    void skipOnboarding() {
        onboardingSteps.completeOnboarding();
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("blocker")})
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Добавление товара в корзину и проверка товара в корзине")
    void addProductToCartTest() {
        catalogSteps.openCatalog();
        String product = catalogSteps.openFirstNewArrivalProduct();
        cartSteps.addToCartWithAnySize();
        cartSteps.openCartFromConfirmation()
                .checkProductInCart(product);
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление товара из корзины")
    void clearCartTest() {
        catalogSteps.openCatalog();
        catalogSteps.openFirstNewArrivalProduct();
        cartSteps.addToCartWithAnySize();
        cartSteps.openCartFromConfirmation()
                .clearCart()
                .checkCartIsEmpty("Корзина пока пуста", "Перейти в каталог");
    }

    @Test
    @Story("Пустая корзина")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("minor")})
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Пустая корзина")
    void emptyCartTest() {
        cartSteps.openCartFromBottomNavigation()
                .checkCartIsEmpty("Корзина пока пуста", "Перейти в каталог");
    }
}
