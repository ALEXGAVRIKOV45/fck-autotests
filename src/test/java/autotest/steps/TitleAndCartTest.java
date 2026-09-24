package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.Locators;
import autotest.pages.PageObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TitleAndCartTest {

    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public TitleAndCartTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("Открывается главная страница магазина")
    public void iOpenThePlaywrightTestPage() {
        Page page = pageObject.getPage();
        page.navigate(playwrightConfig.getTestPageUrl());
        page.waitForCondition(() -> page.locator(Locators.CATALOG_ITEMS_LIST).last().isVisible());
    }

    @When("Проверяем что открыт {string}")
    public void iCheckThePageTitle(String title) {
        String pageTitle = pageObject.getTitle();
        assertTrue(pageTitle.contains(title));
    }

    @When("Количество товаров больше {int}")
    public void iCheckItemsListCount(int itemsCount) {
        assertTrue(pageObject
                .getPage()
                .locator(Locators.CATALOG_ITEMS_LIST)
                .count() > itemsCount);
    }

    @And("Проверить наличие элемента {string}")
    public void iCheckExistElement(String element) {
        Locator locator = null;
        switch (element) {
            case "Футер" ->
                    locator = pageObject
                            .getPage()
                            .locator(Locators.FOOTER_SELECTOR);
            case "Пагинация" ->
                    locator = pageObject
                            .getPage()
                            .locator(Locators.CATALOG_PAGINATION);
            default -> throw new IllegalArgumentException("Не найден элемент:" + element);
        }
        assertTrue(Objects.requireNonNull(locator).isEnabled());
    }

    @And("Проверить видимость элемента {string}")
    public void iCheckVisibleElement(String element) {
        Locator locator = null;
        switch (element) {
            case "Футер" ->
                locator = pageObject
                        .getPage()
                        .locator(Locators.FOOTER_SELECTOR);
            case "Пагинация" ->
                locator = pageObject
                        .getPage()
                        .locator(Locators.CATALOG_PAGINATION);
            default -> throw new IllegalArgumentException("Не найден элемент:" + element);
        }
        assertTrue(Objects.requireNonNull(locator).isVisible());
    }

    @And("Проверить что в футере присутствуют ссылки:")
    public void iCheckLinks(DataTable dataTable) {
        dataTable.asList().forEach(name ->
                assertThat(pageObject
                        .getPage()
                        .locator(Locators.FOOTER_SELECTOR)).containsText(name));
    }

    @And("Прокручивать страницу вниз до появления {string}")
    public void iScrollToElement(String element) {
        Locator locator = null;
        if (element.equalsIgnoreCase("пагинация"))
            locator = pageObject
                    .getPage()
                    .locator(Locators.CATALOG_PAGINATION);
        assert locator != null;
        locator.scrollIntoViewIfNeeded();
    }

    @And("Проверить видимость номера {string} в пагинации")
    public void iCheckForThePaginationNumber(String number) {
        assertTrue(pageObject
                .getPage()
                .locator(Locators.CATALOG_PAGINATION_LINK)
                .filter(new Locator.FilterOptions().setHasText(number)).isVisible());
    }

    @And("Проверить что в блоке пагинации видно {int} элементов")
    public void iCheckVisiblePagination(int itemsCount) {
        assertEquals(pageObject
                .getPage()
                .locator(Locators.CATALOG_PAGINATION_LINK)
                .count(), itemsCount);
    }
}
