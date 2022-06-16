package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import logger.Log;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ProjectsPage extends Form {

    private static final By pageLocator = By.xpath("//title[text()='Union Reporting Web']");
    @Getter private final AddProjectPopUp addProjectPopUp = new AddProjectPopUp();

    private final String projectLinkXpath = "//a[@class='list-group-item']";
    private final IButton addButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(text(), 'Add')]"), "Add Button");

    public ProjectsPage() {
        super(pageLocator, "ProjectsPage");
    }

    public void clickOnProject(String projectName) {
        Log.info("Click on " + projectName);
        ILink project = AqualityServices.getElementFactory().getLink(By.xpath("//a[text()='" + projectName + "']"), "");
        project.state().waitForClickable();
        project.click();
    }

    public void clickOnAdd() {
        Log.info("Click on Add");
        addButton.state().waitForClickable();
        addButton.click();
    }

    private List<String> getProjectsList() {
        List<ILink> elements = AqualityServices.getElementFactory().findElements(By.xpath(projectLinkXpath), ElementType.LINK);
        List<String> names = new ArrayList<>();
        for (ILink element: elements) {
            names.add(element.getText());
        }
        return names;
    }

    public boolean isListContainsProject(String projectName) {
        for (String name: getProjectsList()) {
            if (name.equals(projectName)) return true;
        }
        return false;
    }
}