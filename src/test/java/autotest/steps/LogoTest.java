package autotest.steps;

import autotest.config.PlaywrightConfig;
import autotest.pages.PageObject;
import org.springframework.beans.factory.annotation.Autowired;

public class LogoTest {
    private final PageObject pageObject;
    private final PlaywrightConfig playwrightConfig;

    @Autowired
    public LogoTest(PageObject pageObject, PlaywrightConfig playwrightConfig) {
        this.pageObject = pageObject;
        this.playwrightConfig = playwrightConfig;
    }
}
