package testAutomationSelfEducation.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {

    public ProjectsPage(By locator, String name) {
        super(locator, name);
    }

    public IButton getAddButton() {
        return helpButton;
    }

    /**
     * Constructor with parameters
     *
     * @param locator
     * @param name
     */
    private final IButton helpButton = getElementFactory().getButton(By.xpath("//button[@class='btn btn-xs btn-primary pull-right']"), "button add");
}
