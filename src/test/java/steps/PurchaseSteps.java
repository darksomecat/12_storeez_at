package steps;

import io.qameta.allure.Step;
import pages.MainPage;
import pages.ProductPage;
import pages.SearchPage;
import testdata.TestData;

public class PurchaseSteps {

    private final MainPage mainPage = new MainPage();
    private final SearchPage searchPage = new SearchPage();
    private final ProductPage productPage = new ProductPage();
    private final TestData testData = new TestData();

    @Step("Добавить первый найденный товар в корзину")
    public ProductPage addFirstFoundProductToCart(String searchWord) {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(searchWord)
                .submitSearch()
                .openFirstProductCard();
        productPage.checkOpened(searchWord)
                .selectSize(testData.sizeExcludeText)
                .rememberCard()
                .addToCart(testData.addToCartButton, testData.goToCartButton)
                .openCart(testData.goToCartButton);
        return productPage;
    }
}
