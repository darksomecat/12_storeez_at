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
import static mobile.locators.TwelveStoreezLocators.CATALOG_TAB_NATIVE;
import static mobile.locators.TwelveStoreezLocators.CONTINUE_BUTTON;
import static mobile.locators.TwelveStoreezLocators.LOGIN_BY_EMAIL;
import static mobile.locators.TwelveStoreezLocators.LOGIN_LEGAL_TEXT;
import static mobile.locators.TwelveStoreezLocators.LOGIN_NEXT_BUTTON;
import static mobile.locators.TwelveStoreezLocators.LOGIN_TITLE;
import static mobile.locators.TwelveStoreezLocators.NOTIFICATIONS_BANNER_CLOSE;
import static mobile.locators.TwelveStoreezLocators.NOTIFICATIONS_BANNER_ENABLE;
import static mobile.locators.TwelveStoreezLocators.NOTIFICATIONS_BANNER_TITLE;
import static mobile.locators.TwelveStoreezLocators.NOTIFICATIONS_SHEET_SCRIM;
import static mobile.locators.TwelveStoreezLocators.NOTIFICATIONS_PRIMER_TITLE;
import static mobile.locators.TwelveStoreezLocators.NOT_NOW_BUTTON;
import static mobile.locators.TwelveStoreezLocators.PERMISSION_ALLOW_BUTTON;
import static mobile.locators.TwelveStoreezLocators.PRIMER_BODY;
import static mobile.locators.TwelveStoreezLocators.STARTUP_RETRY_BUTTON;
import static mobile.locators.TwelveStoreezLocators.TRACKING_BODY;
import static mobile.locators.TwelveStoreezLocators.TRACKING_TITLE;

public class OnboardingSteps {

    private static final long SCREEN_WAIT_MS = 30_000;
    private static final long DENY_DIALOG_WAIT_MS = 5_000;
    private static final long MAIN_SCREEN_WAIT_MS = 25_000;
    private static final long DIALOG_WAIT_MS = 3_000;

    @Step("Проверить заголовок и текст экрана запроса push-уведомлений")
    public OnboardingSteps checkNotificationPrimerScreen() {
        $(NOTIFICATIONS_PRIMER_TITLE).shouldBe(visible);
        $(PRIMER_BODY).shouldBe(visible);
        $(CONTINUE_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Закрыть экран запроса уведомлений")
    public OnboardingSteps continueFromNotificationPrimer() {
        $(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Разрешить уведомления в системном диалоге")
    public OnboardingSteps allowNotifications() {
        clickButtonIfAppears(PERMISSION_ALLOW_BUTTON);
        return this;
    }

    @Step("Проверить заголовок и текст экрана персонализации")
    public OnboardingSteps checkTrackingScreen() {
        $(TRACKING_TITLE).shouldBe(visible);
        $(TRACKING_BODY).shouldBe(visible);
        return this;
    }

    @Step("Закрыть экран персонализации")
    public OnboardingSteps continueFromTracking() {
        $(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Проверить заголовок и текст экрана предложения авторизации")
    public OnboardingSteps checkAuthScreen() {
        $(AUTH_TITLE).shouldBe(visible);
        $(AUTH_BODY).shouldBe(visible);
        $(AUTHORIZE_BUTTON).shouldBe(visible);
        $(NOT_NOW_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Пропустить авторизацию")
    public OnboardingSteps skipAuth() {
        $(NOT_NOW_BUTTON).click();
        return this;
    }

    @Step("Перейти к авторизации»")
    public OnboardingSteps openLoginScreen() {
        $(AUTHORIZE_BUTTON).click();
        return this;
    }

    @Step("Проверить атрибуты экрана авторизации")
    public OnboardingSteps checkLoginScreen() {
        $(LOGIN_TITLE).shouldBe(visible);
        $(LOGIN_NEXT_BUTTON).shouldBe(visible);
        $(LOGIN_BY_EMAIL).shouldBe(visible);
        $(LOGIN_LEGAL_TEXT).shouldBe(visible);
        return this;
    }

    @Step("Проверить, что открыт главный экран")
    public OnboardingSteps checkMainScreenOpened() {
        $(CATALOG_TAB_NATIVE).shouldBe(visible);
        return this;
    }

    @Step("Пройти онбординг до главного экрана")
    public OnboardingSteps completeOnboarding() {
        checkNotificationPrimerScreen();
        continueFromNotificationPrimer();
        allowNotifications();
        checkTrackingScreen();
        continueFromTracking();
        checkAuthScreen();
        skipAuth();
        waitForMainScreen();
        return this;
    }

    @Step("Дождаться главного экрана и закрыть согласие о уведомлении")
    public OnboardingSteps waitForMainScreen() {
        long deadline = System.currentTimeMillis() + MAIN_SCREEN_WAIT_MS;
        boolean dialogSeen = false;
        while (System.currentTimeMillis() < deadline) {
            boolean catalogTabShown = !getWebDriver().findElements(CATALOG_TAB_NATIVE).isEmpty();
            boolean dialogShown = isNotificationsDialogShown();
            if (catalogTabShown && !dialogShown) {
                $(CATALOG_TAB_NATIVE).shouldBe(visible);
                return this;
            }
            if (dialogShown) {
                dialogSeen = true;
                closeNotificationsDialog();
            }
            sleep();
        }
        $(CATALOG_TAB_NATIVE).shouldBe(visible);
        return this;
    }

    @Step("Закрыть плашку уведомлений, если она появилась")
    public static void dismissNotificationsDialogIfShown() {
        long deadline = System.currentTimeMillis() + DIALOG_WAIT_MS;
        boolean dialogSeen = false;
        while (System.currentTimeMillis() < deadline) {
            boolean dialogShown = isNotificationsDialogShown();
            if (dialogShown) {
                dialogSeen = true;
                closeNotificationsDialog();
            } else if (dialogSeen) {
                return;
            }
            sleep();
        }
    }

    private static boolean isNotificationsDialogShown() {
        return !getWebDriver().findElements(NOTIFICATIONS_BANNER_TITLE).isEmpty()
                && !getWebDriver().findElements(NOTIFICATIONS_BANNER_ENABLE).isEmpty();
    }

    private static void closeNotificationsDialog() {
        List<WebElement> scrims = getWebDriver().findElements(NOTIFICATIONS_SHEET_SCRIM);
        if (!scrims.isEmpty()) {
            scrims.get(0).click();
            sleep();
            if (!isNotificationsDialogShown()) {
                return;
            }
        }
        $(NOTIFICATIONS_BANNER_ENABLE).click();
        clickButtonIfAppears(PERMISSION_ALLOW_BUTTON);
        sleep();
        if (isNotificationsDialogShown()) {
            $(NOTIFICATIONS_BANNER_CLOSE).click();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Дождаться загрузки приложения или повторить попытку при ошибке запуска")
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

    private static void clickButtonIfAppears(By locator) {
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
