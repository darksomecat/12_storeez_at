package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AppsPage {

    private final SelenideElement page = $(".AppsPage");
    private final SelenideElement title = $(".AppsPage__title");
    private final SelenideElement benefits = $(".AppsPage__text");
    private final SelenideElement qrBlock = $(".AppsPage__qr");
    private final SelenideElement breadcrumbs = $(".AppsPage__breadcrumbs");

    @Step("Проверить заголовок \"{value}\"")
    public AppsPage checkTitle(String value) {
        page.shouldBe(visible);
        title.shouldHave(text(value));
        return this;
    }

    @Step("Проверить содержимое блока установки приложения")
    public AppsPage checkInstallBlockContent() {
        breadcrumbs.shouldHave(text("Приложение 12 STOREEZ"));
        benefits.shouldHave(text("Ранний доступ к новинкам"));
        benefits.shouldHave(text("Мгновенные уведомления о самом важном"));
        benefits.shouldHave(text("Быстрая идентификация на кассе по QR-коду"));
        qrBlock.shouldBe(visible)
                .shouldHave(text("Наведите камеру телефона на QR"));
        $(".AppsPage__qr-image").shouldBe(visible);
        return this;
    }
}
