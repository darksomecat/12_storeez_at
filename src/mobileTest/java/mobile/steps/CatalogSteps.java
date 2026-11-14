package mobile.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static mobile.locators.TwelveStoreezLocators.ADD_TO_CART_BUTTON;
import static mobile.locators.TwelveStoreezLocators.CATALOG_TAB_NATIVE;
import static mobile.locators.TwelveStoreezLocators.FIRST_PRODUCT_CARD;
import static mobile.locators.TwelveStoreezLocators.FIRST_PRODUCT_TITLE;
import static mobile.locators.TwelveStoreezLocators.NOVELTIES_SECTION;
import static mobile.locators.TwelveStoreezLocators.PRODUCT_LIST;

public class CatalogSteps {

    @Step("Открыть каталог по кнопке внизу экрана")
    public CatalogSteps openCatalog() {
        OnboardingSteps.dismissNotificationsDialogIfShown();
        $(CATALOG_TAB_NATIVE).click();
        return this;
    }

    @Step("Открыть первый товар в разделе «Новинки»")
    public String openFirstNewArrivalProduct() {
        $(NOVELTIES_SECTION).click();
        $(PRODUCT_LIST).shouldBe(visible);
        String title = $(FIRST_PRODUCT_TITLE).shouldBe(visible).getText();
        $(FIRST_PRODUCT_CARD).click();
        $(ADD_TO_CART_BUTTON).shouldBe(visible);
        return title;
    }
}
