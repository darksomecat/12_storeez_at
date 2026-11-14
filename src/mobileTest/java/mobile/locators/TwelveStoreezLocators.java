package mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public final class TwelveStoreezLocators {

    private TwelveStoreezLocators() {
    }

    public static By text(String value) {
        return androidUIAutomator("new UiSelector().text(\"" + value + "\")");
    }

    public static By textContains(String value) {
        return androidUIAutomator("new UiSelector().textContains(\"" + value + "\")");
    }

    public static By textMatches(String regex) {
        return androidUIAutomator("new UiSelector().textMatches(\"" + regex + "\")");
    }

    public static String spacedRegex(String value) {
        return Arrays.stream(value.split("\\s+"))
                .map(Pattern::quote)
                .collect(Collectors.joining("\\s+"));
    }

    public static By textWithSpaces(String value) {
        return textMatches(spacedRegex(value));
    }

    public static final By NOTIFICATIONS_PRIMER_TITLE = textWithSpaces("Включите уведомления");
    public static final By PRIMER_BODY = textWithSpaces(
            "Чтобы не пропустить приятные новости — о доставке заказа, наличии желанных моделей и раннем старте SALE");
    public static final By TRACKING_TITLE = textWithSpaces("Будем предугадывать ваши желания");
    public static final By TRACKING_BODY = textWithSpaces(
            "Если разрешите нам видеть ваши запросы. Это поможет сделать приложение более удобным и актуальным для вас.");
    public static final By AUTH_TITLE = textWithSpaces("Осталось авторизоваться");
    public static final By AUTH_BODY = textWithSpaces(
            "Мы сможем сохранить ваш вишлист и корзину, а вам будет удобно копить и тратить бонусы по QR-коду в личном кабинете.");
    public static final By CONTINUE_BUTTON = text("Продолжить");
    public static final By AUTHORIZE_BUTTON = text("Авторизоваться");
    public static final By NOT_NOW_BUTTON = textWithSpaces("Не сейчас");
    public static final By PERMISSION_ALLOW_BUTTON =
            AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button");
    public static final By NOTIFICATIONS_SHEET_SCRIM = AppiumBy.id("com.twelvestoreez:id/touch_outside");
    public static final By NOTIFICATIONS_BANNER_TITLE = AppiumBy.id("com.twelvestoreez:id/textViewTitle");
    public static final By NOTIFICATIONS_BANNER_ENABLE = AppiumBy.id("com.twelvestoreez:id/buttonActionWithoutImageContent");
    public static final By NOTIFICATIONS_BANNER_CLOSE = AppiumBy.id("com.twelvestoreez:id/imageViewClose");
    public static final By STARTUP_RETRY_BUTTON = textMatches("(?i)\\QПовторить\\E\\s+\\Qпопытку\\E");
    public static final By LOGIN_TITLE = textWithSpaces("Войти в профиль 12 STOREEZ");
    public static final By LOGIN_NEXT_BUTTON = text("Далее");
    public static final By LOGIN_BY_EMAIL = textWithSpaces("Войти через эл. почту");
    public static final By LOGIN_LEGAL_TEXT = textWithSpaces(
            "Нажимая кнопку «Далее» я принимаю условия политики конфиденциальности и пользовательского соглашения");
    public static final By CATALOG_TAB = text("КАТАЛОГ");
    public static final By CATALOG_TAB_NATIVE = androidUIAutomator(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/textBottomMenuItem\").text(\"КАТАЛОГ\")");
    public static final By WISHLIST_TAB_NATIVE = androidUIAutomator(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/textBottomMenuItem\").text(\"ВИШЛИСТ\")");
    public static final By CART_TAB_NATIVE = androidUIAutomator(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/textBottomMenuItem\").text(\"КОРЗИНА\")");
    public static final By NOVELTIES_SECTION = text("Новинки");
    public static final By PRODUCT_LIST = AppiumBy.id("com.twelvestoreez:id/recyclerViewProductList");
    public static final By FIRST_PRODUCT_CARD = androidUIAutomator(
            "new UiSelector().resourceId(\"com.twelvestoreez:id/constraintLayoutProduct\").instance(0)");
    public static final By FIRST_PRODUCT_TITLE = org.openqa.selenium.By.xpath(
            "(//*[@resource-id='com.twelvestoreez:id/constraintLayoutProduct'])[1]"
                    + "//*[@resource-id='com.twelvestoreez:id/textViewTitle']");
    public static final By ADD_TO_CART_BUTTON = textWithSpaces("Добавить в корзину");
    public static final By PRODUCT_WISHLIST_BUTTON = org.openqa.selenium.By.xpath(
            "//android.widget.TextView[contains(@text,'Добавить')]"
                    + "/parent::*/parent::*/parent::*/android.widget.Button[1]");
    public static final By ADDED_TO_CART_MESSAGE = textWithSpaces("Добавлено в корзину");
    public static final By GO_TO_CART_BUTTON = textWithSpaces("Перейти в корзину");
    public static final By SIZE_CHIPS =
            androidUIAutomator("new UiSelector().className(\"android.widget.TextView\")"
                    + ".textMatches(\"XS|S|M|L|XL|XXL|[0-9]{2}\").enabled(true)");
    public static final By CLEAR_CART_BUTTON = text("Очистить");
    public static final By ORDER_SUMMARY_LABEL = textWithSpaces("Сумма заказа:");
    public static final By CONFIRM_CLEAR_CART_BUTTON = textMatches("Очистить\\s+корзину");
    public static final By EMPTY_CART_TITLE = textWithSpaces("Корзина пока пуста");
    public static final By GO_TO_CATALOG_BUTTON = textWithSpaces("Перейти в каталог");
    public static final By EMPTY_CART_BUTTON_CONTROL = androidUIAutomator(
            "new UiSelector().className(\"android.widget.Button\").instance(0)");
    public static final By EMPTY_WISHLIST_TITLE = textWithSpaces("Вишлист пока пуст");
    public static final By WISHLIST_DELETE_BUTTON = androidUIAutomator(
            "new UiSelector().className(\"android.widget.Button\").instance(0)");
}
