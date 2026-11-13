package mobile.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static mobile.locators.TwelveStoreezLocators.AUTHORIZE_BUTTON;
import static mobile.locators.TwelveStoreezLocators.AUTH_BODY;
import static mobile.locators.TwelveStoreezLocators.AUTH_TITLE;
import static mobile.locators.TwelveStoreezLocators.CATALOG_TAB;
import static mobile.locators.TwelveStoreezLocators.CONTINUE_BUTTON;
import static mobile.locators.TwelveStoreezLocators.LOGIN_BY_EMAIL;
import static mobile.locators.TwelveStoreezLocators.LOGIN_LEGAL_TEXT;
import static mobile.locators.TwelveStoreezLocators.LOGIN_NEXT_BUTTON;
import static mobile.locators.TwelveStoreezLocators.LOGIN_TITLE;
import static mobile.locators.TwelveStoreezLocators.NOTIFICATIONS_PRIMER_TITLE;
import static mobile.locators.TwelveStoreezLocators.NOT_NOW_BUTTON;
import static mobile.locators.TwelveStoreezLocators.PERMISSION_DENY_BUTTON;
import static mobile.locators.TwelveStoreezLocators.PRIMER_BODY;
import static mobile.locators.TwelveStoreezLocators.STARTUP_RETRY_BUTTON;
import static mobile.locators.TwelveStoreezLocators.TRACKING_BODY;
import static mobile.locators.TwelveStoreezLocators.TRACKING_TITLE;

public class OnboardingSteps {

    private static final long SCREEN_WAIT_MS = 30_000;
    private static final long DENY_DIALOG_WAIT_MS = 5_000;

    @Step("Проверить, что отображается экран запроса push-уведомлений")
    public OnboardingSteps checkNotificationPrimerScreen() {
        $(NOTIFICATIONS_PRIMER_TITLE).shouldBe(visible);
        $(PRIMER_BODY).shouldBe(visible);
        $(CONTINUE_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Перейти с экрана запроса уведомлений по кнопке «Продолжить»")
    public OnboardingSteps continueFromNotificationPrimer() {
        $(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Отказаться от уведомлений в системном диалоге, если он появился")
    public OnboardingSteps denyNotifications() {
        clickButtonIfAppears(PERMISSION_DENY_BUTTON);
        return this;
    }

    @Step("Проверить, что отображается экран персонализации")
    public OnboardingSteps checkTrackingScreen() {
        $(TRACKING_TITLE).shouldBe(visible);
        $(TRACKING_BODY).shouldBe(visible);
        return this;
    }

    @Step("Перейти с экрана персонализации по кнопке «Продолжить»")
    public OnboardingSteps continueFromTracking() {
        $(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Проверить, что отображается экран предложения авторизации")
    public OnboardingSteps checkAuthScreen() {
        $(AUTH_TITLE).shouldBe(visible);
        $(AUTH_BODY).shouldBe(visible);
        $(AUTHORIZE_BUTTON).shouldBe(visible);
        $(NOT_NOW_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Пропустить авторизацию по кнопке «Не сейчас»")
    public OnboardingSteps skipAuth() {
        $(NOT_NOW_BUTTON).click();
        return this;
    }

    @Step("Перейти к авторизации по кнопке «Авторизоваться»")
    public OnboardingSteps openLoginScreen() {
        $(AUTHORIZE_BUTTON).click();
        return this;
    }

    @Step("Проверить, что отображается экран входа в профиль")
    public OnboardingSteps checkLoginScreen() {
        $(LOGIN_TITLE).shouldBe(visible);
        $(LOGIN_NEXT_BUTTON).shouldBe(visible);
        $(LOGIN_BY_EMAIL).shouldBe(visible);
        $(LOGIN_LEGAL_TEXT).shouldBe(visible);
        return this;
    }

    @Step("Проверить, что открыт главный экран")
    public OnboardingSteps checkMainScreenOpened() {
        $(CATALOG_TAB).shouldBe(visible);
        return this;
    }

    @Step("Пройти онбординг до главного экрана")
    public OnboardingSteps completeOnboarding() {
        checkNotificationPrimerScreen();
        continueFromNotificationPrimer();
        denyNotifications();
        checkTrackingScreen();
        continueFromTracking();
        checkAuthScreen();
        skipAuth();
        checkMainScreenOpened();
        return this;
    }

    @Step("Дождаться загрузки приложения, повторяя попытку при ошибке запуска")
    public OnboardingSteps recoverFromStartupErrorIfShown() {
        long deadline = System.currentTimeMillis() + SCREEN_WAIT_MS;
        while (System.currentTimeMillis() < deadline) {
            if (!getWebDriver().findElements(NOTIFICATIONS_PRIMER_TITLE).isEmpty()
                    || !getWebDriver().findElements(TRACKING_TITLE).isEmpty()
                    || !getWebDriver().findElements(AUTH_TITLE).isEmpty()
                    || !getWebDriver().findElements(CATALOG_TAB).isEmpty()) {
                return this;
            }
            List<WebElement> retryButtons = getWebDriver().findElements(STARTUP_RETRY_BUTTON);
            if (!retryButtons.isEmpty()) {
                try {
                    retryButtons.get(0).click();
                } catch (StaleElementReferenceException stale) {
                }
            }
        }
        throw new IllegalStateException("Приложение не вышло с экрана загрузки");
    }

    private void clickButtonIfAppears(By locator) {
        long deadline = System.currentTimeMillis() + DENY_DIALOG_WAIT_MS;
        while (System.currentTimeMillis() < deadline) {
            List<WebElement> buttons = getWebDriver().findElements(locator);
            if (!buttons.isEmpty()) {
                try {
                    buttons.get(0).click();
                    return;
                } catch (StaleElementReferenceException stale) {
                }
            }
        }
    }
}
