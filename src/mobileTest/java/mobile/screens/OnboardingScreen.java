package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class OnboardingScreen {

    private final String primerTitleText = "Включите уведомления";
    private final String trackingTitleText = "Будем предугадывать ваши желания";
    private final String authTitleText = "Осталось авторизоваться";
    private final String continueButtonText = "Продолжить";
    private final String notNowButtonText = "Не сейчас";
    private final String authorizeButtonText = "Авторизоваться";

    private final SelenideElement primerTitle = Selectors.textWithSpaces(primerTitleText);
    private final SelenideElement primerBody = Selectors.textWithSpaces(
            "Чтобы не пропустить приятные новости — о доставке заказа, наличии желанных моделей и раннем старте SALE");
    private final SelenideElement continueButton = Selectors.text(continueButtonText);
    private final SelenideElement trackingTitle = Selectors.textWithSpaces(trackingTitleText);
    private final SelenideElement trackingBody = Selectors.textWithSpaces(
            "Если разрешите нам видеть ваши запросы. Это поможет сделать приложение более удобным и актуальным для вас.");
    private final SelenideElement authTitle = Selectors.textWithSpaces(authTitleText);
    private final SelenideElement authBody = Selectors.textWithSpaces(
            "Мы сможем сохранить ваш вишлист и корзину, а вам будет удобно копить и тратить бонусы по QR-коду в личном кабинете.");
    private final SelenideElement authorizeButton = Selectors.text(authorizeButtonText);
    private final SelenideElement notNowButton = Selectors.textWithSpaces(notNowButtonText);

    private final SelenideElement permissionAllowButton = $(AppiumBy.id(
            "com.android.permissioncontroller:id/permission_allow_button"));
    private final SelenideElement sheetTitle = Selectors.id("textViewTitle");
    private final SelenideElement sheetEnableButton = Selectors.id("buttonActionWithoutImageContent");
    private final SelenideElement sheetScrim = Selectors.id("touch_outside");
    private final SelenideElement sheetCloseButton = Selectors.id("imageViewClose");
    private final SelenideElement startupRetryButton = Selectors.textMatches("(?i)\\QПовторить\\E\\s+\\Qпопытку\\E");

    @Step("Проверить, что отображается экран запроса push-уведомлений")
    public void verifyPrimerScreenOpen() {
        primerTitle.shouldBe(visible);
        primerBody.shouldBe(visible);
        continueButton.shouldBe(visible);
    }

    @Step("Перейти с экрана запроса уведомлений по кнопке «{this.continueButtonText}»")
    public void continueFromPrimer() {
        continueButton.click();
    }

    @Step("Разрешить уведомления в системном диалоге, если он появился")
    public void allowNotifications() {
        clickButtonIfAppears(permissionAllowButton);
    }

    @Step("Проверить, что отображается экран персонализации")
    public void verifyTrackingScreenOpen() {
        trackingTitle.shouldBe(visible);
        trackingBody.shouldBe(visible);
    }

    @Step("Перейти с экрана персонализации по кнопке «{this.continueButtonText}»")
    public void continueFromTracking() {
        continueButton.click();
    }

    @Step("Проверить, что отображается экран предложения авторизации")
    public void verifyAuthScreenOpen() {
        authTitle.shouldBe(visible);
        authBody.shouldBe(visible);
        authorizeButton.shouldBe(visible);
        notNowButton.shouldBe(visible);
    }

    @Step("Пропустить авторизацию по кнопке «{this.notNowButtonText}»")
    public void skipAuth() {
        notNowButton.click();
    }

    @Step("Перейти к авторизации по кнопке «{this.authorizeButtonText}»")
    public void openLoginScreen() {
        authorizeButton.click();
    }

    @Step("Дождаться загрузки приложения, повторяя попытку при ошибке запуска")
    public void waitForAppStart() {
        long deadline = System.currentTimeMillis() + SCREEN_WAIT_MS;
        while (System.currentTimeMillis() < deadline) {
            if (anyOnboardingScreenShown()) {
                return;
            }
            if (startupRetryButton.exists()) {
                try {
                    startupRetryButton.click();
                } catch (Throwable ignored) {
                }
            }
            sleep();
        }
        throw new IllegalStateException("Приложение не вышло с экрана загрузки");
    }

    @Step("Закрыть плашку уведомлений, если она появилась")
    public void dismissNotificationsSheetIfShown() {
        long deadline = System.currentTimeMillis() + SHEET_GUARD_MS;
        boolean sheetSeen = false;
        while (System.currentTimeMillis() < deadline) {
            if (isNotificationsSheetShown()) {
                sheetSeen = true;
                closeNotificationsSheet();
            } else if (sheetSeen) {
                return;
            }
            sleep();
        }
    }

    boolean isNotificationsSheetShown() {
        return sheetTitle.exists() && sheetEnableButton.exists();
    }

    void closeNotificationsSheet() {
        if (sheetScrim.exists()) {
            try {
                sheetScrim.click();
            } catch (Throwable ignored) {
            }
            sleep();
            if (!isNotificationsSheetShown()) {
                return;
            }
        }
        clickButtonIfAppears(sheetEnableButton);
        if (isNotificationsSheetShown()) {
            sheetCloseButton.click();
        }
    }

    private boolean anyOnboardingScreenShown() {
        return primerTitle.exists()
                || trackingTitle.exists()
                || authTitle.exists()
                || sheetTitle.exists();
    }

    private void clickButtonIfAppears(SelenideElement button) {
        try {
            button.shouldBe(visible, DIALOG_WAIT).click();
        } catch (Throwable ignored) {
        }
    }

    private static final long SCREEN_WAIT_MS = 30_000;
    private static final Duration DIALOG_WAIT = Duration.ofSeconds(5);
    private static final long SHEET_GUARD_MS = 3_000;
    private static final long POLL_MS = 300;

    private static void sleep() {
        try {
            Thread.sleep(POLL_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
