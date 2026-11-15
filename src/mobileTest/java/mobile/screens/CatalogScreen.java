package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class CatalogScreen {

    private final String noveltiesSectionText = "Новинки";

    private final SelenideElement noveltiesSection = Selectors.text(noveltiesSectionText);
    private final SelenideElement productList = Selectors.id("recyclerViewProductList");
    private final SelenideElement firstProductTitle = Selectors.xpath(
            "(//*[@resource-id='com.twelvestoreez:id/constraintLayoutProduct'])[1]"
                    + "//*[@resource-id='com.twelvestoreez:id/textViewTitle']");
    private final SelenideElement firstProductCard = Selectors.uia(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/constraintLayoutProduct\").instance(0)");

    @Step("Открыть раздел «{this.noveltiesSectionText}»")
    public void openNovelties() {
        noveltiesSection.click();
    }

    @Step("Проверить, что список товаров загрузился")
    public void verifyProductListLoaded() {
        productList.shouldBe(visible);
    }

    @Step("Получить название первого товара в списке")
    public String getFirstProductTitle() {
        return firstProductTitle.shouldBe(visible).getText();
    }

    @Step("Открыть карточку первого товара")
    public void openFirstProduct() {
        firstProductCard.click();
    }
}
