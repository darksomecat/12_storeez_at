package mobile.screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

final class Selectors {

    private Selectors() {
    }

    static SelenideElement id(String resourceId) {
        return $(AppiumBy.id("com.twelvestoreez:id/" + resourceId));
    }

    static SelenideElement text(String value) {
        return $(androidUIAutomator("new UiSelector().text(\"" + value + "\")"));
    }

    static SelenideElement textContains(String value) {
        return $(androidUIAutomator("new UiSelector().textContains(\"" + value + "\")"));
    }

    static SelenideElement textMatches(String regex) {
        return $(androidUIAutomator("new UiSelector().textMatches(\"" + regex + "\")"));
    }

    static SelenideElement textWithSpaces(String value) {
        return textMatches(spacedRegex(value));
    }

    static SelenideElement xpath(String xpath) {
        return $(By.xpath(xpath));
    }

    static SelenideElement uia(String selector) {
        return $(androidUIAutomator(selector));
    }

    static ElementsCollection uiaAll(String selector) {
        return $$(androidUIAutomator(selector));
    }

    private static String spacedRegex(String value) {
        return Arrays.stream(value.split("\\s+"))
                .map(Pattern::quote)
                .collect(Collectors.joining("\\s+"));
    }
}
