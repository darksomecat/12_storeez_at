package mobile.screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class ProductScreen {

    private final SelenideElement addToCartButton = Selectors.textWithSpaces("Добавить в корзину");
    private final ElementsCollection sizeChips = Selectors.uiaAll(
            "new UiSelector().className(\"android.widget.TextView\")"
                    + ".textMatches(\"XS|S|M|L|XL|XXL|[0-9]{2}\").enabled(true)");
    private final SelenideElement addedToCartMessage = Selectors.textWithSpaces("Добавлено в корзину");
    private final SelenideElement goToCartButton = Selectors.textWithSpaces("Перейти в корзину");
    private final SelenideElement wishlistButton = Selectors.xpath(
            "//android.widget.TextView[contains(@text,'Добавить')]"
                    + "/parent::*/parent::*/parent::*/android.widget.Button[1]");

    @Step("Проверить, что открыта карточка товара")
    public void verifyProductScreenOpen() {
        addToCartButton.shouldBe(visible);
    }

    @Step("Нажать «Добавить в корзину»")
    public void addToCart() {
        addToCartButton.click();
    }

    @Step("Выбрать первый доступный размер")
    public String selectFirstAvailableSize() {
        String size = sizeChips.first().shouldBe(visible).getText();
        sizeChips.first().click();
        return size;
    }

    @Step("Проверить, что товар добавлен в корзину")
    public void verifyAddedToCart() {
        addedToCartMessage.shouldBe(visible);
    }

    @Step("Перейти в корзину")
    public void goToCart() {
        goToCartButton.click();
    }

    @Step("Добавить товар в вишлист")
    public void addToWishlist() {
        wishlistButton.click();
    }

    @Step("Вернуться назад")
    public void navigateBack() {
        WebDriverRunner.getWebDriver().navigate().back();
    }
}
