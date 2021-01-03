package testAutomationSelfEducation.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProjectPage extends Form {

    /**
     * Constructor with parameters
     *
     * @param locator
     * @param name
     */
    public MyProjectPage(By locator, String name) {
        super(locator, name);
    }

    public ITextBox getInputMyProjectName() {
        return myInputProjectName;
    }

    private final ITextBox myInputProjectName = getElementFactory().getTextBox(By.xpath("//input[@id='projectName']"), "input project Name");

    public String getNameMyProject(String myNameProject) {
        return AqualityServices.getBrowser().getDriver().findElement(By.xpath("//a[text()='" + myNameProject + "']")).getText();
    }

    public String getNameMyTest(String testForMe) {
        return AqualityServices.getBrowser().getDriver().findElement(By.xpath("//p[text()='" + testForMe + "']")).getText();
    }
    public String getEnvironmentName(String localhost) {
        return AqualityServices.getBrowser().getDriver().findElement(By.xpath("//p[text()='" + localhost + "']")).getText();
    }

    public void clickNameMyProject(String myNameProject) {
        AqualityServices.getBrowser().getDriver().findElement(By.xpath("//a[text()='" + myNameProject + "']")).click();
    }

    public void clickMyTest(String testForMe) {
        AqualityServices.getBrowser().getDriver().findElement(By.xpath("//a[text()='" + testForMe + "']")).click();
    }

}
