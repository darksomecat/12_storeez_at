package pages;

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
    public AppsPage checkInstallBlockContent(String breadcrumb, String qrHint, String... benefitTexts) {
        breadcrumbs.shouldHave(text(breadcrumb));
        for (String benefitText : benefitTexts) {
            benefits.shouldHave(text(benefitText));
        }
        qrBlock.shouldBe(visible)
                .shouldHave(text(qrHint));
        $(".AppsPage__qr-image").shouldBe(visible);
        return this;
    }
}
