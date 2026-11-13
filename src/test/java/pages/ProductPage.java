package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {

    private final SelenideElement page = $(".ProductPage");
    private final SelenideElement title = $(".ProductSummary__title");
    private final SelenideElement sizeSelector = $(".ProductSizeSelector__active");
    private final SelenideElement sizeValue = $(".ProductSizeSelector__active-label");
    private final SelenideElement sizeList = $(".selectList");
    private final ElementsCollection sizeItems = $$(".selectList__item");
    private final ElementsCollection productButtons = $$(".ProductButtons__button");
    private final SelenideElement wishlistButton = $(".ProductButtons__button--wishlist");
    private final ElementsCollection wishlistLoginButtons = $$(".WishlistLoginPopup__button");
    private final SelenideElement wishlistLoginPopup = $(".WishlistLoginPopup__content");
    private final SelenideElement wishlistLoginTitle = $(".WishlistLoginPopup__title");
    private final SelenideElement wishlistLoginText = $(".WishlistLoginPopup__text");
    private final SelenideElement wishlistLoginNext = $(".WishlistLoginPopup__controls a.WishlistLoginPopup__button");
    private final SelenideElement addedToCartPopup = $$(".defaultPopupWrapper").findBy(text("Товар добавлен в корзину"));
    private final SelenideElement continueShoppingButton = addedToCartPopup.$(byText("Продолжить покупки"));
    private String productTitle;
    private String selectedSize;

    @Step("Проверить, что открыта страница товара с названием, содержащим \"{titlePart}\"")
    public ProductPage checkOpened(String titlePart) {
        page.shouldBe(visible);
        title.shouldBe(visible).shouldHave(text(titlePart));
        return this;
    }

    @Step("Выбрать первый доступный размер")
    public ProductPage selectSize(String excludeText) {
        sizeSelector.shouldBe(visible).click();
        sizeList.shouldBe(visible);
        sizeItems.excludeWith(text(excludeText)).first().shouldBe(visible).click();
        return this;
    }

    @Step("Запомнить название и размер на карточке")
    public ProductPage rememberCard() {
        productTitle = title.shouldBe(visible).getText().trim();
        selectedSize = sizeValue.shouldBe(visible).getText().split("·")[0].trim();
        return this;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    @Step("Добавить товар в корзину")
    public ProductPage addToCart(String addButton, String goToCartButton) {
        productButtons.findBy(text(addButton))
                .shouldBe(visible)
                .click();
        productButtons.findBy(text(goToCartButton))
                .shouldBe(visible);
        return this;
    }

    @Step("Перейти в корзину со страницы товара")
    public ProductPage openCart(String goToCartButton) {
        closeAddedToCartPopupIfPresent();
        productButtons.findBy(text(goToCartButton))
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Закрыть поп-ап «Товар добавлен в корзину», если появился")
    private ProductPage closeAddedToCartPopupIfPresent() {
        if (addedToCartPopup.isDisplayed()) {
            continueShoppingButton.click();
            addedToCartPopup.shouldBe(hidden);
        }
        return this;
    }

    @Step("Добавить товар в вишлист")
    public ProductPage addToWishlist() {
        wishlistButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить поп-ап логина вишлиста для неавторизованного пользователя")
    public ProductPage checkWishlistLoginPopup(String title, String text, String nextButton) {
        wishlistLoginPopup.shouldBe(visible);
        wishlistLoginTitle.shouldHave(text(title));
        wishlistLoginText.shouldHave(text(text));
        wishlistLoginNext.shouldBe(visible)
                .shouldHave(text(nextButton));
        return this;
    }

    @Step("Закрыть поп-ап логина вишлиста")
    public ProductPage closeWishlistLoginPopup(String closeButton) {
        wishlistLoginButtons.findBy(text(closeButton))
                .shouldBe(visible)
                .click();
        wishlistLoginPopup.shouldBe(hidden);
        return this;
    }
}
