package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class AppsPage {

    private final SelenideElement page = $(".AppsPage");
    private final SelenideElement pageTitle = $(".AppsPage__title");
    private final SelenideElement benefitsBlock = $(".AppsPage__text");
    private final SelenideElement qrBlock = $(".AppsPage__qr");
    private final SelenideElement qrImage = $(".AppsPage__qr-image");
    private final SelenideElement breadcrumbs = $(".AppsPage__breadcrumbs");
    private final SelenideElement appStoreButton = $(".AppsPage__buttons a[href*='iosbutton']");
    private final SelenideElement googlePlayButton = $(".AppsPage__buttons a[href*='androidbutton']");

    @Step("Проверить заголовок \"{title}\"")
    public AppsPage checkTitle(String title) {
        page.shouldBe(visible);
        pageTitle.shouldHave(text(title));
        return this;
    }

    @Step("Проверить содержимое блока установки приложения")
    public AppsPage checkInstallBlockContent(String breadcrumb, String qrHint, String... benefitTexts) {
        breadcrumbs.shouldHave(text(breadcrumb));
        for (String benefitText : benefitTexts) {
            benefitsBlock.shouldHave(text(benefitText));
        }
        qrBlock.shouldBe(visible)
                .shouldHave(text(qrHint));
        qrImage.shouldBe(visible);
        return this;
    }

    @Step("Нажать на кнопку «Загрузите в App Store»")
    public AppsPage clickAppStoreButton() {
        appStoreButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку «Доступно в Google Play»")
    public AppsPage clickGooglePlayButton() {
        googlePlayButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что в магазине по адресу \"{expectedUrlPart}\" открыто приложение \"{appName}\"")
    public AppsPage checkStorePageOpened(String expectedUrlPart, String appName) {
        switchTo().window(1);
        webdriver().shouldHave(urlContaining(expectedUrlPart));
        $("h1").shouldHave(text(appName));
        closeWindow();
        switchTo().window(0);
        return this;
    }
}
