package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WishlistPage {

    private final SelenideElement pageTitle = $("body");

    @Step("Проверить, что вишлист содержит товар \"{namePart}\"")
    public WishlistPage checkProductPresent(String namePart) {
        pageTitle.shouldHave(text("Вишлист"));
        $$(".ProductCard__title, .ProductCard").findBy(text(namePart)).shouldBe(visible);
        return this;
    }
}
