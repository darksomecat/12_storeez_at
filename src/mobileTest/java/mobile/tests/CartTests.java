package mobile.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mobile.steps.CartSteps;
import mobile.steps.CatalogSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Очистка корзины: пустая корзина")
    void clearCartTest() {
        catalogSteps.openCatalog();
        catalogSteps.openFirstNewArrivalProduct();
        cartSteps.addToCartWithAnySize();
        cartSteps.openCartFromConfirmation()
                .clearCart()
                .checkCartIsEmpty();
    }

    @Test
    @Story("Пустая корзина")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Пустая корзина сразу после запуска")
    void emptyCartTest() {
        cartSteps.openCartFromBottomNavigation()
                .checkCartIsEmpty();
    }
}
