package mobile.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static mobile.locators.TwelveStoreezLocators.EMPTY_WISHLIST_TITLE;
import static mobile.locators.TwelveStoreezLocators.GO_TO_CATALOG_BUTTON;
import static mobile.locators.TwelveStoreezLocators.PRODUCT_WISHLIST_BUTTON;
import static mobile.locators.TwelveStoreezLocators.WISHLIST_DELETE_BUTTON;
import static mobile.locators.TwelveStoreezLocators.WISHLIST_TAB;
import static mobile.locators.TwelveStoreezLocators.textContains;

public class WishlistSteps {

    @Step("Добавить товар в список избранного с карточки товара")
    public WishlistSteps addToWishlistFromProductCard() {
        $(PRODUCT_WISHLIST_BUTTON).shouldBe(visible).click();
        return this;
    }

    @Step("Открыть список избранного через нижнюю навигацию")
    public WishlistSteps openWishlistFromBottomNavigation() {
        $(WISHLIST_TAB).shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что в списке избранного отображается товар «{title}»")
    public WishlistSteps checkProductInWishlist(String title) {
        $(textContains(title)).shouldBe(visible);
        return this;
    }

    @Step("Удалить товар из списка избранного по кнопке удаления")
    public WishlistSteps removeProductFromWishlist() {
        $(WISHLIST_DELETE_BUTTON).shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что список избранного пуст")
    public WishlistSteps checkWishlistIsEmpty() {
        $(EMPTY_WISHLIST_TITLE).shouldBe(visible);
        $(GO_TO_CATALOG_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Нажать «Назад»")
    public WishlistSteps pressBack() {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        return this;
    }
}
