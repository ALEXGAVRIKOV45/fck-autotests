package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.pages.PageObject;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest {
    public final PageObject pageObject;
    public final PlaywrightConfig playwrightConfig;

    @Autowired
    public BaseTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }

    @Given("ПАУЗА")
    public void iOpenThePlaywrightTestPage() {
        pageObject.getPage().pause();
    }
}
