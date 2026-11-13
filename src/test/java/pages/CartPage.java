package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {

    private final SelenideElement cartContainer = $(".cart");
    private final SelenideElement emptyCart = $(".cart--empty");
    private final SelenideElement emptyStateTitle = $(".EmptyResult__title");
    private final SelenideElement goodsItem = $(".order-list__item .goods");
    private final SelenideElement goodsTitle = $(".order-list__item .goods__title");
    private final SelenideElement goodsSize = $(".order-list__item .goods-edit__size .select-text");
    private final SelenideElement goodsCount = $(".order-list__item .quantity-input input");
    private final SelenideElement goodsPrice = $(".order-list__item .goods__price");
    private final SelenideElement sizeSelectControl = $(".order-list__item .goods-edit__size .select-control");
    private final ElementsCollection sizeOptions = $$(".order-list__item .goods-edit__size .options .option");
    private final SelenideElement quantityPlus = $(".order-list__item .quantity-input .button-plus");
    private final SelenideElement quantityMinus = $(".order-list__item .quantity-input .button-minus");
    private final SelenideElement removeIcon = $(".order-list__item .goods-edit__remove-block");
    private final ElementsCollection removeConfirmDialogButtons = $$(".simplert__footer");
    private final SelenideElement geoConfirmButton = $(".UserGeoConfirmTooltip").$$(".ButtonV2__text").findBy(text("Да, верно"));

    @Step("Проверить, что корзина пустая")
    public CartPage checkEmpty(String emptyTitle) {
        emptyCart.shouldBe(visible);
        emptyStateTitle.shouldHave(text(emptyTitle));
        return this;
    }

    @Step("Проверить соответствие товара в корзине с карточкой")
    public CartPage checkAddedProduct(String title, String size, String quantity) {
        cartContainer.shouldBe(visible);
        emptyCart.shouldNot(exist);
        goodsItem.shouldBe(visible);
        goodsTitle.shouldHave(exactText(title));
        goodsSize.shouldHave(exactText(size));
        goodsCount.shouldHave(value(quantity));
        return this;
    }

    @Step("Запомнить текущий размер товара")
    public String getCurrentSize() {
        return goodsSize.shouldBe(visible).getText().trim();
    }

    @Step("Запомнить текущую сумму товара")
    public String getCurrentPrice() {
        return goodsPrice.shouldBe(visible).getText();
    }

    @Step("Выбрать в списке размер, отличный от текущего")
    public CartPage selectAnotherSize() {
        String currentSize = getCurrentSize();
        sizeSelectControl.shouldBe(visible).click();
        sizeOptions.excludeWith(text(currentSize)).first().shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что размер изменился относительно \"{sizeBefore}\"")
    public CartPage checkSizeChanged(String sizeBefore) {
        goodsSize.shouldBe(visible).shouldNotHave(exactText(sizeBefore));
        return this;
    }

    @Step("Увеличить количество товара")
    public CartPage increaseQuantity() {
        quantityPlus.shouldBe(visible).click();
        return this;
    }

    @Step("Уменьшить количество товара")
    public CartPage decreaseQuantity() {
        quantityMinus.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что количество стало \"{expectedQuantity}\"")
    public CartPage checkQuantity(String expectedQuantity) {
        goodsCount.shouldHave(value(expectedQuantity));
        return this;
    }

    @Step("Проверить, что сумма увеличилась вдвое от \"{priceBefore}\"")
    public CartPage checkPriceDoubled(String priceBefore) {
        goodsPrice.shouldNotHave(text(priceBefore));
        assertEquals(parsePrice(priceBefore) * 2, parsePrice(goodsPrice.shouldBe(visible).getText()));
        return this;
    }

    @Step("Проверить, что сумма осталась прежней относительно \"{priceBefore}\"")
    public CartPage checkPriceUnchanged(String priceBefore) {
        goodsPrice.shouldHave(text(priceBefore));
        return this;
    }

    @Step("Удалить товар из корзины")
    public CartPage removeProduct(String confirmButton) {
        closeGeoTooltip();
        removeIcon.shouldBe(visible).click();
        removeConfirmDialogButtons.findBy(text(confirmButton)).shouldBe(visible).click();
        emptyCart.shouldBe(visible);
        return this;
    }

    private long parsePrice(String price) {
        return Long.parseLong(price.replaceAll("[^\\d]", ""));
    }

    @Step("Подтвердить геолокацию, если поп-ап перекрывает страницу")
    private void closeGeoTooltip() {
        if (geoConfirmButton.isDisplayed()) {
            geoConfirmButton.click();
        }
    }
}
