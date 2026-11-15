package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class LoginScreen {

    private final SelenideElement screenTitle = Selectors.textWithSpaces(
            "Войти в профиль 12 STOREEZ");
    private final SelenideElement phoneInput = Selectors.textWithSpaces("Далее");
    private final SelenideElement emailLoginButton = Selectors.textWithSpaces(
            "Войти через эл. почту");
    private final SelenideElement legalNotice = Selectors.textWithSpaces(
            "Нажимая кнопку «Далее» я принимаю условия политики конфиденциальности и пользовательского соглашения");

    @Step("Проверить, что отображается экран авторизации")
    public void verifyLoginScreenOpen() {
        screenTitle.shouldBe(visible);
        phoneInput.shouldBe(visible);
        emailLoginButton.shouldBe(visible);
        legalNotice.shouldBe(visible);
    }
}
