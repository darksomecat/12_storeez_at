package tests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {

    private final SelenideElement page = $(".ProductPage");
    private final SelenideElement title = $(".ProductSummary__title");
    private final SelenideElement sizeSelector = $(".ProductSizeSelector__active");
    private final SelenideElement sizeList = $(".selectList");
    private final ElementsCollection sizeItems = $$(".selectList__item");
    private final SelenideElement wishlistButton = $(".ProductButtons__button--wishlist");
    private final SelenideElement wishlistLoginPopup = $(".WishlistLoginPopup__content");
    private final SelenideElement wishlistLoginTitle = $(".WishlistLoginPopup__title");
    private final SelenideElement wishlistLoginText = $(".WishlistLoginPopup__text");
    private final SelenideElement wishlistLoginNext = $(".WishlistLoginPopup__controls a.WishlistLoginPopup__button");
    private final SelenideElement wishlistLoginClose = $$(".WishlistLoginPopup__button").findBy(text("Закрыть"));

    @Step("Проверить, что страница товара открыта")
    public ProductPage checkOpened() {
        page.shouldBe(visible);
        title.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что название содержит \"{value}\"")
    public ProductPage checkTitleContains(String value) {
        title.shouldHave(text(value));
        return this;
    }

    @Step("Выбрать первый доступный размер")
    public ProductPage selectSize() {
        sizeSelector.shouldBe(visible).click();
        sizeList.shouldBe(visible);
        sizeItems.excludeWith(text("Подписка")).first().shouldBe(visible).click();
        return this;
    }

    @Step("Добавить товар в корзину")
    public ProductPage addToCart() {
        $$(".ProductButtons__button")
                .findBy(text("Добавить в корзину"))
                .shouldBe(visible)
                .click();
        $$(".ProductButtons__button")
                .findBy(text("Перейти в корзину"))
                .shouldBe(visible);
        return this;
    }

    @Step("Перейти в корзину со страницы товара")
    public ProductPage openCart() {
        $$(".ProductButtons__button")
                .findBy(text("Перейти в корзину"))
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Добавить товар в вишлист")
    public ProductPage addToWishlist() {
        wishlistButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить попап логина вишлиста для неавторизованного пользователя")
    public ProductPage checkWishlistLoginPopup() {
        wishlistLoginPopup.shouldBe(visible);
        wishlistLoginTitle.shouldHave(text("Вход и регистрация"));
        wishlistLoginText.shouldHave(text("Войдите в аккаунт, чтобы смотреть свой вишлист на любом устройстве"));
        wishlistLoginNext.shouldBe(visible)
                .shouldHave(text("Далее"));
        return this;
    }

    @Step("Закрыть попап логина вишлиста")
    public ProductPage closeWishlistLoginPopup() {
        wishlistLoginClose.shouldBe(visible).click();
        wishlistLoginPopup.shouldBe(hidden);
        return this;
    }
}
