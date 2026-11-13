package mobile.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mobile.steps.CatalogSteps;
import mobile.steps.WishlistSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("aukolova")
@Feature("Список избранного")
public class WishlistTests extends TestBase {

    private final CatalogSteps catalogSteps = new CatalogSteps();
    private final WishlistSteps wishlistSteps = new WishlistSteps();

    @BeforeEach
    void skipOnboarding() {
        onboardingSteps.completeOnboarding();
    }

    @Test
    @Story("Управление избранным")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление товара в список избранного и проверка товара в списке избранного")
    void addProductToWishlistTest() {
        catalogSteps.openCatalog();
        String product = catalogSteps.openFirstNewArrivalProduct();
        wishlistSteps.addToWishlistFromProductCard()
                .pressBack()
                .openWishlistFromBottomNavigation()
                .checkProductInWishlist(product);
    }

    @Test
    @Story("Управление избранным")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление товара из списка избранного")
    void removeProductFromWishlistTest() {
        catalogSteps.openCatalog();
        String product = catalogSteps.openFirstNewArrivalProduct();
        wishlistSteps.addToWishlistFromProductCard()
                .pressBack()
                .openWishlistFromBottomNavigation()
                .checkProductInWishlist(product)
                .removeProductFromWishlist()
                .checkWishlistIsEmpty();
    }
}
