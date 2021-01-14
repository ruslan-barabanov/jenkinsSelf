package testAutomationSelfEducation;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import testAutomationSelfEducation.util.FluentApi;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {
    protected static final String DEFAULT_URL = "http://login:password@localhost:8080/web";
    protected final IElementFactory elementFactory;
    protected final FluentApi fluentApi = new FluentApi();

    protected BaseTest() {
        elementFactory = AqualityServices.getElementFactory();
    }

    @BeforeMethod
    protected void beforeMethod() {

        AqualityServices.getBrowser().goTo(DEFAULT_URL);
        getBrowser().setWindowSize(2000, 768);
        AqualityServices.getBrowser().getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }

}
