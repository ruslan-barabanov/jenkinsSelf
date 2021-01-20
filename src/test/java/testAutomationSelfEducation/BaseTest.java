package testAutomationSelfEducation;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import testAutomationSelfEducation.util.FluentApi;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public abstract class BaseTest {
    protected final IElementFactory elementFactory;
    protected final FluentApi fluentApi = new FluentApi();

    protected BaseTest() {
        elementFactory = AqualityServices.getElementFactory();
    }
    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
    @BeforeMethod
    protected void beforeMethod() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));
        AqualityServices.getBrowser().goTo(properties.getProperty("default_url.path"));
        getBrowser().setWindowSize(2000, 768);
        AqualityServices.getBrowser().getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }



}
