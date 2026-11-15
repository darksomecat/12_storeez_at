package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.JiraIssue;
import io.qameta.allure.JiraIssues;
import io.qameta.allure.Layer;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.ProductPage;

@Layer("web")
@Owner("aukolova")
@Feature("Корзина")
public class CartTests extends TestBase {

    private ProductPage addFirstFoundProductToCart(String searchWord) {
        mainPage.openPage()
                .openSearch();
        searchPage.typeQuery(searchWord)
                .submitSearch()
                .openFirstProductCard();
        ProductPage addedProduct = productPage.checkOpened(searchWord)
                .selectSize(testData.sizeExcludeText)
                .rememberCard()
                .addToCart(testData.addToCartButton, testData.goToCartButton)
                .openCart(testData.goToCartButton);
        Selenide.sleep(3000);
        return addedProduct;
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("blocker")})
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Добавление товара в корзину и проверка товара в корзине")
    void addProductToCartTest() {
        ProductPage addedProduct = addFirstFoundProductToCart(testData.searchWord);
        cartPage.checkAddedProduct(
                addedProduct.getProductTitle(),
                addedProduct.getSelectedSize(),
                testData.cartItemQuantity
        );
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("critical")})
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление товара из корзины")
    void removeProductFromCartTest() {
        ProductPage addedProduct = addFirstFoundProductToCart(testData.searchWord);
        cartPage.removeProduct(testData.removeFromCartConfirm)
                .checkProductAbsent(addedProduct.getProductTitle());
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("minor")})
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Пустая корзина")
    void emptyCartTest() {
        mainPage.openPage()
                .openCartFromHeader();
        cartPage.checkEmpty(testData.emptyCartText);
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Смена размера товара в корзине")
    void changeProductSizeInCartTest() {
        addFirstFoundProductToCart(testData.searchWord);
        String sizeBefore = cartPage.getCurrentSize();
        cartPage.selectAnotherSize()
                .checkSizeChanged(sizeBefore);
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Увеличение количества товара в корзине: сумма удваивается")
    void increaseProductQuantityInCartTest() {
        addFirstFoundProductToCart(testData.searchWord);
        String priceBefore = cartPage.getCurrentPrice();
        cartPage.increaseQuantity()
                .checkQuantity("2")
                .checkPriceDoubled(priceBefore);
    }

    @Test
    @Story("Управление товарами в корзине")
    @JiraIssues({@JiraIssue("MUL-58")})
    @Tags({@Tag("web"), @Tag("normal")})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Уменьшение количества товара в корзине: сумма возвращается к исходной")
    void decreaseProductQuantityInCartTest() {
        addFirstFoundProductToCart(testData.searchWord);
        String priceBefore = cartPage.getCurrentPrice();
        cartPage.increaseQuantity()
                .checkQuantity("2")
                .decreaseQuantity()
                .checkQuantity("1")
                .checkPriceUnchanged(priceBefore);
    }
}
