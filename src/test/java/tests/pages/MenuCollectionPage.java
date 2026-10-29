package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MenuCollectionPage {

    private final SelenideElement menuDesktop = $(".MenuDesktop");

    @Step("Проверить ссылки коллекции Женщинам")
    public MenuCollectionPage womenCollection() {
        checkLinks(
                "Новинки",
                "Гид по верхней одежде",
                "Самое теплое",
                "Одежда",
                "Обувь",
                "Сумки и аксессуары",
                "Материалы 12 STOREEZ",
                "Фактуры осени",
                "Деловой гардероб",
                "Капсула GI",
                "Спортивная капсула",
                "Бестселлеры",
                "Летний гардероб",
                "Вечерняя коллекция",
                "Подарки",
                "Смотреть все"
        );
        return this;
    }

    @Step("Проверить ссылки коллекции Мужчинам")
    public MenuCollectionPage manCollection() {
        checkLinks(
                "Новинки",
                "Гид по верхней одежде",
                "Самое теплое",
                "Одежда",
                "Обувь",
                "Сумки и аксессуары",
                "Материалы 12 STOREEZ",
                "Фактуры осени",
                "Деловой гардероб",
                "Спортивная капсула",
                "Бестселлеры",
                "Летний гардероб",
                "Вечерняя коллекция",
                "Подарки",
                "Смотреть все"
        );
        return this;
    }

    private void checkLinks(String... titles) {
        for (String title : titles) {
            menuDesktop.$(byText(title)).shouldBe(visible);
        }
    }
}
