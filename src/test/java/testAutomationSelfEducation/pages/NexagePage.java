package testAutomationSelfEducation.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NexagePage extends Form {

    public NexagePage(By locator, String name) {
        super(locator, name);
    }

    private final ITextBox nexage = getElementFactory().getTextBox(By.xpath("//a[text()='Nexage']"), "Nexage");

    public ITextBox getNexage() {
        return nexage;
    }

    public List<WebElement> getNexageTestsUi() {
        return AqualityServices.getBrowser().getDriver().findElements(By.xpath("//td[contains(text(),'com.nexage.tests.')]"));
    }


}
