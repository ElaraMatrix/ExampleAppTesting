package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.BrowserUtils;
import utils.SIDUtility;

public class BaseTest {

    @BeforeSuite
    public void generateSID() {
        SIDUtility.getSID();
    }

    @BeforeMethod
    public void setUp() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.browserQuit();
    }
}