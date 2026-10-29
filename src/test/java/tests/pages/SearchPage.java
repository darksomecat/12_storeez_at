package tests.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    private final SelenideElement searchInput = $(".HeaderSearch__field input.TextInput__input");
    private final ElementsCollection searchTaps = $$(".SearchSection__content .HeaderSearch__tap");
    private final ElementsCollection productCards = $$(".ProductCard");
    private final ElementsCollection productTitles = $$(".ProductCard__title");
    private final SelenideElement searchResultTitle = $("body");
    private String selectedSearchTap;

    @Step("Ввести в поиск \"{query}\"")
    public SearchPage typeQuery(String query) {
        searchInput.shouldBe(visible).setValue(query);
        return this;
    }

    @Step("Отправить поиск Enter")
    public SearchPage submitSearch() {
        searchInput.pressEnter();
        return this;
    }

    @Step("Проверить заголовок результата по запросу \"{query}\"")
    public SearchPage checkResultHeadline(String query) {
        searchResultTitle.shouldHave(text("Результат поиска по запросу"));
        searchResultTitle.shouldHave(text(query));
        return this;
    }

    @Step("Проверить, что карточки содержат \"{query}\"")
    public SearchPage checkProductCardsContain(String query) {
        productCards.shouldHave(CollectionCondition.sizeGreaterThan(0));
        productTitles.filter(visible).shouldHave(CollectionCondition.sizeGreaterThan(0));
        productTitles.filter(visible).forEach(title -> title.shouldHave(text(query)));
        return this;
    }

    @Step("Дождаться подсказок поиска")
    public SearchPage checkSearchTapsVisible() {
        searchTaps.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Кликнуть первую подсказку поиска")
    public SearchPage clickFirstSearchTap() {
        SelenideElement tap = searchTaps.first().shouldBe(visible);
        selectedSearchTap = tap.getText().trim();
        tap.click();
        return this;
    }

    public String getSelectedSearchTap() {
        return selectedSearchTap;
    }

    @Step("Проверить результаты по подсказке \"{selectedSearchTap}\"")
    public SearchPage checkResultsMatchSelectedTap() {
        checkResultHeadline(selectedSearchTap);
        checkProductCardsContain(selectedSearchTap);
        return this;
    }

    @Step("Открыть первую карточку товара")
    public SearchPage openFirstProductCard() {
        productCards.first().$(".ProductCard__link, a").shouldBe(visible).click();
        return this;
    }
}
