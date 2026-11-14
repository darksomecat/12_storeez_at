package mobile.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.JiraIssue;
import io.qameta.allure.JiraIssues;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mobile.steps.CatalogSteps;
import mobile.steps.WishlistSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Layer("mobile")
@Owner("aukolova")
@Feature("Wishlist")
public class WishlistTests extends TestBase {

    private final CatalogSteps catalogSteps = new CatalogSteps();
    private final WishlistSteps wishlistSteps = new WishlistSteps();

    @BeforeEach
    void skipOnboarding() {
        onboardingSteps.completeOnboarding();
    }

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление и проверка товара в вкладке \"Список избранного\"")
    void addProductToWishlistTest() {
        catalogSteps.openCatalog();
        String product = catalogSteps.openFirstNewArrivalProduct();
        wishlistSteps.addToWishlistFromProductCard()
                .pressBack()
                .openWishlistFromBottomNavigation()
                .checkProductInWishlist(product);
    }

    @Test
    @Story("Управление товарами в списке избранного")
    @JiraIssues({@JiraIssue("MUL-78")})
    @Tags({@Tag("mobile"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление товара из \"Списка избранного\"")
    void removeProductFromWishlistTest() {
        catalogSteps.openCatalog();
        String product = catalogSteps.openFirstNewArrivalProduct();
        wishlistSteps.addToWishlistFromProductCard()
                .pressBack()
                .openWishlistFromBottomNavigation()
                .checkProductInWishlist(product)
                .removeProductFromWishlist()
                .checkWishlistIsEmpty("Вишлист пока пуст", "Перейти в каталог");
    }
}
