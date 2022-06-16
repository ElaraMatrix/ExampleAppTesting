package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import logger.Log;
import models.TestRecord;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class AllTestsPage extends Form {

    private static final By pageLocator = By.xpath("//table[@class='table']");
    private final String tableRowXpath = "//table[@class='table']//tr";
    private final String nameColumnXpath = "/td[1]/a";
    private final String methodColumnXpath = "/td[2]";
    private final String resultColumnXpath = "/td[3]/span";
    private final String startTimeColumnXpath = "/td[4]";
    private final String endTimeColumnXpath = "/td[5]";
    private final String durationColumnXpath = "/td[6]";


    public AllTestsPage() {
        super(pageLocator, "All Tests Page");
    }

    public List<TestRecord> getTestsTableRows() {
        Log.info("Get test records from all tests page");
        List<ILabel> rows = AqualityServices.getElementFactory().findElements(By.xpath(tableRowXpath), ElementType.LABEL);
        List<TestRecord> testRecords = new ArrayList<>();
        IElementFactory elements = AqualityServices.getElementFactory();
        for (int i = 2; i < rows.size() + 1; i++) {
            String name = elements.getLabel(By.xpath(tableRowXpath + "[" + i + "]" + nameColumnXpath), "").getText();
            String method = elements.getLabel(By.xpath(tableRowXpath + "[" + i + "]" + methodColumnXpath), "").getText();
            String result = elements.getLabel(By.xpath(tableRowXpath + "[" + i + "]" + resultColumnXpath), "").getText();
            String startTime = elements.getLabel(By.xpath(tableRowXpath + "[" + i + "]" + startTimeColumnXpath), "").getText();
            String endTime = elements.getLabel(By.xpath(tableRowXpath + "[" + i + "]" + endTimeColumnXpath), "").getText();
            String duration = elements.getLabel(By.xpath(tableRowXpath + "[" + i + "]" + durationColumnXpath), "").getText();
            testRecords.add(new TestRecord(duration, method, name, startTime, endTime, result));
        }
        return testRecords;
    }

    public boolean isTestRecordAdded(TestRecord testRecord) {
        return AqualityServices.getElementFactory().getLabel(By.xpath("//a[text()='" + testRecord.getTestName() + "']"), "").state().waitForDisplayed()
        && AqualityServices.getElementFactory().getLabel(By.xpath("//td[text()='" + testRecord.getTestMethod() + "']"), "").state().waitForDisplayed();
    }
}