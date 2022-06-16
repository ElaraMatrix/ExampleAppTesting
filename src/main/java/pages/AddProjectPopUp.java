package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.KeyboardUtils;

public class AddProjectPopUp extends Form {

    private static final By pageLocator = By.xpath("//h4[@id='title']");
    private final ITextBox projectName = AqualityServices.getElementFactory().getTextBox(By.id("projectName"), "Project Name Field");
    private final IButton saveProject = AqualityServices.getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Save Project Button");
    private final ILabel successSaved = AqualityServices.getElementFactory().getLabel(By.xpath("//div[contains(@class, 'alert-success')]"), "Success Saved Label");

    public AddProjectPopUp() {
        super(pageLocator, "Add Project PopUp");
    }

    public boolean typeProjectNameAndSave(String name) {
        projectName.type(name);
        saveProject.click();
        return successSaved.state().waitForDisplayed();
    }

    public void closePopUp() {
        KeyboardUtils.keyESC();
    }
}