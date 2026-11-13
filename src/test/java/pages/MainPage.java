package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final SelenideElement menuButton = $(".HeaderMenuButton");
    private final SelenideElement menuDesktop = $(".MenuDesktop");
    private final SelenideElement appsMenuLink = $("a[href='/info/apps']");
    private final SelenideElement searchIcon = $(".Header__icons--right div.header-icon");
    private final SelenideElement searchInput = $(".HeaderSearch__field input.TextInput__input");
    private final SelenideElement wishlistHeaderIcon = $("a.header-icon[href='/profile/wishlist']");
    private final SelenideElement cartHeaderIcon = $("a.header-icon[href='/cart']");
    private final SelenideElement popmechanicPopup = $(".popmechanic-content");
    private final SelenideElement popmechanicClose = $(".popmechanic-close-text");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        dismissPopups();
        return this;
    }

    @Step("Закрыть всплывающие окна на главной")
    private MainPage dismissPopups() {
        try {
            if (popmechanicPopup.isDisplayed()) {
                popmechanicClose.click();
            }
        } catch (Throwable ignored) {
        }
        return this;
    }

    @Step("Открыть левое меню")
    public MainPage openLeftMenu() {
        menuButton.hover();
        menuDesktop.shouldBe(visible);
        return this;
    }

    @Step("Перейти в раздел приложения из меню")
    public MainPage openAppsFromMenu() {
        openLeftMenu();
        appsMenuLink.shouldBe(visible).click();
        return this;
    }

    @Step("Открыть поиск")
    public MainPage openSearch() {
        searchIcon.click();
        searchInput.shouldBe(visible);
        return this;
    }

    @Step("Открыть вишлист из шапки")
    public MainPage openWishlistFromHeader() {
        wishlistHeaderIcon.click();
        return this;
    }

    @Step("Открыть корзину из шапки")
    public MainPage openCartFromHeader() {
        cartHeaderIcon.click();
        return this;
    }
}
