package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import models.TestRecord;
import org.openqa.selenium.logging.LogType;
import org.testng.*;
import org.testng.annotations.Test;
import pages.AllTestsPage;
import pages.ProjectsPage;
import utils.APIUtils;
import utils.BrowserUtils;
import utils.JsonReader;
import utils.Randomizer;
import utils.parameters.APIContentType;

import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;

@Test(testName = "Exam Test")
public class ExamTest extends BaseTest {

    private final JsonSettingsFile settings = new JsonSettingsFile("settings.json");

    @Test
    public void variant2(Method method) {
        ProjectsPage projectsPage = new ProjectsPage();
        AllTestsPage allTestsPage = new AllTestsPage();

        BrowserUtils.goToDefaultURL();
        BrowserUtils.addTokenCookie();
        BrowserUtils.refreshPage();
        projectsPage.state().waitForExist();

        projectsPage.clickOnProject(settings.getValue("/project_name").toString());
        allTestsPage.state().waitForExist();
        List<TestRecord> testRecords = allTestsPage.getTestsTableRows();
        List<TestRecord> testRecordsAPI = JsonReader
                .getModelsList(TestRecord[].class, APIUtils.getTestsJson(settings.getValue("/project_id").toString()));
        Assert.assertTrue(TestRecord.isListSortedByStartTime(testRecords), "Test records isn't sorted");
        Assert.assertTrue(TestRecord.isRecordsListContainsList(testRecordsAPI, testRecords), "Test records doesn't match");

        BrowserUtils.goToPreviousPage();
        projectsPage.state().waitForExist();
        String projectName = Randomizer.getRandomWord();
        Assert.assertFalse(projectsPage.isListContainsProject(projectName), "Project has already been on page");
        projectsPage.clickOnAdd();
        projectsPage.getAddProjectPopUp().state().waitForDisplayed();
        Assert.assertTrue(projectsPage.getAddProjectPopUp().typeProjectNameAndSave(projectName), "Project hasn't been saved");
        projectsPage.getAddProjectPopUp().closePopUp();
        projectsPage.getAddProjectPopUp().state().waitForNotDisplayed();
        BrowserUtils.refreshPage();
        Assert.assertTrue(projectsPage.isListContainsProject(projectName), "Project hasn't been added");

        projectsPage.clickOnProject(projectName);
        allTestsPage.state().waitForExist();
        TestRecord currentTest = new TestRecord(getClass().getName(), method.getName());
        String testID = APIUtils.putTestRecord(projectName, currentTest);
        String screenshot = Base64.getEncoder().encodeToString(AqualityServices.getBrowser().getScreenshot());
        APIUtils.putAttachment(testID, screenshot, APIContentType.IMAGE);
        APIUtils.putLog(testID, AqualityServices.getBrowser().getLogs(LogType.BROWSER));
        Assert.assertTrue(allTestsPage.isTestRecordAdded(currentTest), "Test record hasn't been added");
    }
}