package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement cart = $(".cart");
    private final SelenideElement emptyCart = $(".cart--empty");
    private final SelenideElement emptyTitle = $(".EmptyResult__title");
    private final SelenideElement goodsItem = $(".order-list__item .goods, .goods");
    private final SelenideElement goodsTitle = $(".goods__title");
    private final SelenideElement removeIcon = $(".goods-edit__remove-block");

    @Step("Проверить, что корзина пустая")
    public CartPage checkEmpty(String emptyText) {
        emptyCart.shouldBe(visible);
        emptyTitle.shouldHave(text(emptyText));
        return this;
    }

    @Step("Проверить, что в корзине есть товар \"{namePart}\"")
    public CartPage checkProductPresent(String namePart) {
        cart.shouldBe(visible);
        emptyCart.shouldNot(exist);
        goodsItem.shouldBe(visible);
        goodsTitle.shouldHave(text(namePart));
        return this;
    }

    @Step("Удалить товар из корзины")
    public CartPage removeProduct() {
        removeIcon.click();
        SelenideElement confirm = $(byText("Удалить"));
        confirm.shouldBe(visible).click();
        emptyCart.shouldBe(visible);
        return this;
    }
}
