package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MenuCollectionPage {

    private final SelenideElement menuDesktop = $(".MenuDesktop");

    @Step("Проверить ссылки коллекции")
    public MenuCollectionPage checkCollectionLinks(String... titles) {
        for (String title : titles) {
            menuDesktop.$(byText(title)).shouldBe(visible);
        }
        return this;
    }
}
