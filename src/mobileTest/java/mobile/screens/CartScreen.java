package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class CartScreen {

    private final String emptyCartTitleText = "Корзина пока пуста";
    private final String goToCatalogButtonText = "Перейти в каталог";

    private final SelenideElement orderSummaryLabel = Selectors.textWithSpaces("Сумма заказа:");
    private final SelenideElement clearCartButton = Selectors.text("Очистить");
    private final SelenideElement confirmClearCartButton = Selectors.textMatches("Очистить\\s+корзину");
    private final SelenideElement emptyCartTitle = Selectors.textWithSpaces(emptyCartTitleText);
    private final SelenideElement goToCatalogButton = Selectors.textWithSpaces(goToCatalogButtonText);

    @Step("Проверить, что открыта корзина")
    public void verifyCartScreenOpen() {
        orderSummaryLabel.shouldBe(visible);
    }

    @Step("Проверить, что товар «{title}» отображается в корзине")
    public void verifyProductInCart(String title) {
        Selectors.textContains(title).shouldBe(visible);
    }

    @Step("Начать очистку корзины")
    public void startClearing() {
        orderSummaryLabel.shouldBe(visible);
        clearCartButton.click();
    }

    @Step("Подтвердить очистку корзины")
    public void confirmClearCart() {
        confirmClearCartButton.click();
    }

    @Step("Проверить, что корзина пуста: заголовок «{this.emptyCartTitleText}»"
            + " и кнопка «{this.goToCatalogButtonText}»")
    public void verifyCartIsEmpty() {
        emptyCartTitle.shouldBe(visible);
        goToCatalogButton.shouldBe(visible);
    }
}
