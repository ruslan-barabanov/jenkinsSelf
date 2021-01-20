package testAutomationSelfEducation.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {

    public ProjectsPage(By locator, String name) {
        super(locator, name);
    }

    private final IButton helpButton = getElementFactory().getButton(By.xpath("//button[contains(text(),'+Add')]"), "button add");
    private final ITextBox versionName = getElementFactory().getTextBox(By.xpath("//p[contains(text(),'Reporting')]/span"), "version Name");

    public IButton getAddButton() {
        return helpButton;
    }

    public IElement getVersionName() {
        return versionName;
    }

}
