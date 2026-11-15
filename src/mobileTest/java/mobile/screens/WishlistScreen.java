package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class WishlistScreen {

    private final String emptyWishlistTitleText = "Вишлист пока пуст";
    private final String goToCatalogButtonText = "Перейти в каталог";

    private final SelenideElement deleteButton = Selectors.uia(
            "new UiSelector().className(\"android.widget.Button\").instance(0)");
    private final SelenideElement emptyWishlistTitle = Selectors.textWithSpaces(emptyWishlistTitleText);
    private final SelenideElement goToCatalogButton = Selectors.textWithSpaces(goToCatalogButtonText);

    @Step("Проверить, что товар «{title}» отображается в вишлисте")
    public void verifyProductInWishlist(String title) {
        Selectors.textContains(title).shouldBe(visible);
    }

    @Step("Удалить товар из вишлиста")
    public void removeProduct() {
        deleteButton.click();
    }

    @Step("Проверить, что вишлист пуст: заголовок «{this.emptyWishlistTitleText}»"
            + " и кнопка «{this.goToCatalogButtonText}»")
    public void verifyWishlistIsEmpty() {
        emptyWishlistTitle.shouldBe(visible);
        goToCatalogButton.shouldBe(visible);
    }
}
