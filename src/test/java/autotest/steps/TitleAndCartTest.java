package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.locators.Locators;
import autotest.pages.PageObject;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.microsoft.playwright.options.LoadState.DOMCONTENTLOADED;
import static com.microsoft.playwright.options.LoadState.LOAD;
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

}
