package mobile.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static mobile.locators.TwelveStoreezLocators.EMPTY_WISHLIST_TITLE;
import static mobile.locators.TwelveStoreezLocators.GO_TO_CATALOG_BUTTON;
import static mobile.locators.TwelveStoreezLocators.PRODUCT_WISHLIST_BUTTON;
import static mobile.locators.TwelveStoreezLocators.WISHLIST_DELETE_BUTTON;
import static mobile.locators.TwelveStoreezLocators.WISHLIST_TAB_NATIVE;
import static mobile.locators.TwelveStoreezLocators.textContains;

public class WishlistSteps {

    @Step("Добавить товар в список избранного")
    public WishlistSteps addToWishlistFromProductCard() {
        $(PRODUCT_WISHLIST_BUTTON).click();
        return this;
    }

    @Step("Открыть список избранного через нижнюю навигацию")
    public WishlistSteps openWishlistFromBottomNavigation() {
        OnboardingSteps.dismissNotificationsDialogIfShown();
        $(WISHLIST_TAB_NATIVE).click();
        return this;
    }

    @Step("Проверить, что в списке избранного отображается товар «{title}»")
    public WishlistSteps checkProductInWishlist(String title) {
        $(textContains(title)).shouldBe(visible);
        return this;
    }

    @Step("Удалить товар из списка избранного")
    public WishlistSteps removeProductFromWishlist() {
        $(WISHLIST_DELETE_BUTTON).click();
        return this;
    }

    @Step("Проверить заголовок «{title}» и наличие кнопки «{button}» в пустом списке избранного")
    public WishlistSteps checkWishlistIsEmpty(String title, String button) {
        $(EMPTY_WISHLIST_TITLE).shouldBe(visible);
        $(GO_TO_CATALOG_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Нажать «Назад»")
    public WishlistSteps pressBack() {
        WebDriverRunner.getWebDriver().navigate().back();
        return this;
    }
}
