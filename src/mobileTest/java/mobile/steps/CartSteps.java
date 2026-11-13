package mobile.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static mobile.locators.TwelveStoreezLocators.ADDED_TO_CART_MESSAGE;
import static mobile.locators.TwelveStoreezLocators.ADD_TO_CART_BUTTON;
import static mobile.locators.TwelveStoreezLocators.CART_TAB;
import static mobile.locators.TwelveStoreezLocators.CLEAR_CART_BUTTON;
import static mobile.locators.TwelveStoreezLocators.CONFIRM_CLEAR_CART_BUTTON;
import static mobile.locators.TwelveStoreezLocators.EMPTY_CART_BUTTON_CONTROL;
import static mobile.locators.TwelveStoreezLocators.EMPTY_CART_TITLE;
import static mobile.locators.TwelveStoreezLocators.GO_TO_CART_BUTTON;
import static mobile.locators.TwelveStoreezLocators.GO_TO_CATALOG_BUTTON;
import static mobile.locators.TwelveStoreezLocators.SIZE_CHIPS;
import static mobile.locators.TwelveStoreezLocators.textContains;

public class CartSteps {

    @Step("Добавить товар в корзину, выбрав первый доступный размер")
    public String addToCartWithAnySize() {
        $(ADD_TO_CART_BUTTON).click();
        SelenideElement sizeChip = $$(SIZE_CHIPS).first().shouldBe(visible);
        String size = sizeChip.getText();
        sizeChip.click();
        $(ADDED_TO_CART_MESSAGE).shouldBe(visible);
        return size;
    }

    @Step("Перейти в корзину по кнопке подтверждения")
    public CartSteps openCartFromConfirmation() {
        $(GO_TO_CART_BUTTON).click();
        $(CLEAR_CART_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Очистить корзину с подтверждением")
    public CartSteps clearCart() {
        $(CLEAR_CART_BUTTON).shouldBe(visible).click();
        $(CONFIRM_CLEAR_CART_BUTTON).shouldBe(visible).click();
        return this;
    }

    @Step("Открыть корзину через нижнюю навигацию")
    public CartSteps openCartFromBottomNavigation() {
        $(CART_TAB).shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что в корзине отображается товар «{title}»")
    public CartSteps checkProductInCart(String title) {
        $(textContains(title)).shouldBe(visible);
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public CartSteps checkCartIsEmpty() {
        $(EMPTY_CART_TITLE).shouldBe(visible);
        $(GO_TO_CATALOG_BUTTON).shouldBe(visible);
        $(EMPTY_CART_BUTTON_CONTROL).shouldBe(visible);
        return this;
    }
}
