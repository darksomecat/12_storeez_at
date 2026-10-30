package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final SelenideElement menuButton = $(".HeaderMenuButton");
    private final SelenideElement menuDesktop = $(".MenuDesktop");
    private final SelenideElement genderTabs = $(".MenuGenderTabs");
    private final SelenideElement searchIcon = $(".Header__icons--right div.header-icon");
    private final SelenideElement searchInput = $(".HeaderSearch__field input.TextInput__input");
    private final SelenideElement wishlistHeaderIcon = $("a.header-icon[href='/profile/wishlist']");
    private final SelenideElement cartHeaderIcon = $("a.header-icon[href='/cart']");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        dismissPopups();
        return this;
    }

    @Step("Закрыть всплывающие окна на главной")
    private MainPage dismissPopups() {
        SelenideElement noThanks = $(byText("Нет, спасибо"));
        if (noThanks.exists() && noThanks.isDisplayed()) {
            noThanks.click();
        }
        return this;
    }

    @Step("Открыть левое меню")
    public MainPage openLeftMenu() {
        menuButton.hover();
        menuDesktop.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что левое меню открыто")
    public MainPage checkMenuOpened() {
        menuDesktop.shouldBe(visible);
        genderTabs.shouldBe(visible);
        return this;
    }

    @Step("Выбрать вкладку \"{gender}\" в левом меню")
    public MainPage selectMenuGender(String gender) {
        genderTabs.$(byText(gender)).click();
        genderTabs.$(".MenuGenderTabs__item--active")
                .shouldHave(text(gender));
        return this;
    }

    @Step("Проверить активную вкладку меню \"{gender}\"")
    public MainPage checkActiveMenuGender(String gender) {
        genderTabs.$(".MenuGenderTabs__item--active")
                .shouldHave(text(gender));
        return this;
    }

    @Step("Закрыть левое меню")
    public MainPage closeLeftMenu() {
        menuButton.click();
        menuDesktop.shouldBe(hidden);
        return this;
    }

    @Step("Проверить, что левое меню закрыто")
    public MainPage checkMenuClosed() {
        menuDesktop.shouldBe(hidden);
        return this;
    }

    @Step("Перейти в раздел приложения из меню")
    public MainPage openAppsFromMenu() {
        openLeftMenu();
        $("a[href='/info/apps']").shouldBe(visible).click();
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
