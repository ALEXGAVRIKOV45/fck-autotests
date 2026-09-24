package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.pages.PageObject;
import org.springframework.beans.factory.annotation.Autowired;

public class CatalogItemsTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public CatalogItemsTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }
}
