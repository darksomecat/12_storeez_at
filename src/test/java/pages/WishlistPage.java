package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

public class WishlistPage {

    private final SelenideElement pageBody = $("body");
    private final ElementsCollection productCards = $$(".ProductCard__title, .ProductCard");
    private final SelenideElement productCardTitle = $(".ProductCard__title");
    private final SelenideElement addToCartButton = $(".ProductCard .ProductCard__button");
    private final SelenideElement sizePopup = $(".SizesPopup__wrapper");
    private final SelenideElement sizePopupTitle = $(".defaultPopupHead__title");
    private final SelenideElement sizePopupClose = $(".defaultPopupHead__close-button");
    private final ElementsCollection sizePopupItems = $$(".SizesList .SizesListItem--clickable");
    private final SelenideElement productWishlistButton = $(".ProductCard .ProductCardWishlistButton");
    private final SelenideElement emptyState = $(".WishlistPage__empty");
    private final SelenideElement emptyStateTitle = $(".EmptyResult__title");
    private final SelenideElement emptyStateDescription = $(".EmptyResult__description");
    private final SelenideElement catalogButton = $(".EmptyResult__button");
    private String selectedSize;

    @Step("Проверить, что вишлист содержит товар \"{namePart}\"")
    public WishlistPage checkProductPresent(String pageTitle, String namePart) {
        pageBody.shouldHave(text(pageTitle));
        productCards.findBy(text(namePart)).shouldBe(visible);
        return this;
    }

    @Step("Запомнить название товара в вишлисте")
    public String getProductCardTitle() {
        return productCardTitle.shouldBe(visible).getText().trim();
    }

    @Step("Нажать на кнопку \"{button}\" у товара в вишлисте")
    public WishlistPage clickAddToCartButton(String button) {
        addToCartButton.shouldBe(visible).shouldHave(text(button)).click();
        return this;
    }

    @Step("Проверить поп-ап выбора размера")
    public WishlistPage checkSizePopupOpened(String popupTitle) {
        sizePopup.shouldBe(visible);
        sizePopupTitle.shouldHave(text(popupTitle));
        sizePopupClose.shouldBe(visible);
        sizePopupItems.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Закрыть поп-ап выбора размера по крестику")
    public WishlistPage closeSizePopup() {
        sizePopupClose.shouldBe(visible).click();
        sizePopup.shouldBe(hidden);
        return this;
    }

    @Step("Выбрать случайный размер, кроме \"{excludeText}\"")
    public WishlistPage selectRandomSize(String excludeText) {
        SelenideElement size = sizePopupItems.filter(visible)
                .excludeWith(text(excludeText))
                .shouldHave(sizeGreaterThan(0))
                .first();
        selectedSize = size.getText().split("\n")[0].trim();
        size.click();
        sizePopup.shouldBe(hidden);
        return this;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    @Step("Проверить, что у товара \"{namePart}\" иконка вишлиста активна")
    public WishlistPage checkProductWishlistActive(String namePart) {
        productCards.findBy(text(namePart)).shouldBe(visible);
        productWishlistButton.shouldHave(cssClass("ProductCardWishlistButton--active"));
        return this;
    }

    @Step("Нажать на иконку вишлиста у товара")
    public WishlistPage clickProductWishlistButton() {
        productWishlistButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что иконка вишлиста стала неактивной, а товар \"{namePart}\" остался на странице")
    public WishlistPage checkProductWishlistInactive(String namePart) {
        productWishlistButton.shouldNotHave(cssClass("ProductCardWishlistButton--active"));
        productCards.findBy(text(namePart)).shouldBe(visible);
        return this;
    }

    @Step("Обновить страницу")
    public WishlistPage refreshPage() {
        refresh();
        return this;
    }

    @Step("Проверить, что вишлист пустой")
    public WishlistPage checkEmpty(String expectedPageTitle, String expectedEmptyTitle, String expectedEmptyDescription, String expectedButton) {
        pageBody.shouldHave(text(expectedPageTitle));
        emptyState.shouldBe(visible);
        emptyStateTitle.shouldHave(text(expectedEmptyTitle));
        emptyStateDescription.shouldHave(text(expectedEmptyDescription));
        catalogButton.shouldBe(visible).shouldHave(text(expectedButton));
        return this;
    }
}
