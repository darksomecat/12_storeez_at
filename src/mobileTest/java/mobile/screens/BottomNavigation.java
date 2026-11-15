package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class BottomNavigation {

    private final OnboardingScreen onboardingScreen = new OnboardingScreen();

    private final SelenideElement catalogTab = Selectors.uia(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/textBottomMenuItem\").text(\"КАТАЛОГ\")");
    private final SelenideElement wishlistTab = Selectors.uia(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/textBottomMenuItem\").text(\"ВИШЛИСТ\")");
    private final SelenideElement cartTab = Selectors.uia(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/textBottomMenuItem\").text(\"КОРЗИНА\")");

    @Step("Проверить, что вкладка «Каталог» отображается")
    public void verifyCatalogTabVisible() {
        catalogTab.shouldBe(visible);
    }

    @Step("Открыть каталог")
    public void openCatalog() {
        onboardingScreen.dismissNotificationsSheetIfShown();
        catalogTab.click();
    }

    @Step("Открыть вишлист")
    public void openWishlist() {
        onboardingScreen.dismissNotificationsSheetIfShown();
        wishlistTab.click();
    }

    @Step("Открыть корзину")
    public void openCart() {
        onboardingScreen.dismissNotificationsSheetIfShown();
        cartTab.click();
    }

    @Step("Дождаться загрузки главной страницы")
    public void waitForCatalogTab() {
        long deadline = System.currentTimeMillis() + WAIT_MS;
        while (System.currentTimeMillis() < deadline) {
            if (catalogTab.exists() && !onboardingScreen.isNotificationsSheetShown()) {
                return;
            }
            if (onboardingScreen.isNotificationsSheetShown()) {
                onboardingScreen.closeNotificationsSheet();
            }
            sleep();
        }
        catalogTab.shouldBe(visible);
    }

    private static final long WAIT_MS = 25_000;
    private static final long POLL_MS = 300;

    private static void sleep() {
        try {
            Thread.sleep(POLL_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
