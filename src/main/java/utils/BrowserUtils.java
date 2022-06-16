package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.JsonSettingsFile;
import logger.Log;
import org.openqa.selenium.Cookie;

public final class BrowserUtils {

    private BrowserUtils() {}

    public static void goToDefaultURL() {
        Log.info("Go to default URL");
        AqualityServices.getBrowser().goTo(new JsonSettingsFile("settings.json").getValue("/default_url").toString());
    }

    public static void addTokenCookie() {
        Log.info("Add token as cookie");
        AqualityServices.getBrowser().getDriver().manage().addCookie(new Cookie("token", APIUtils.getToken()));
    }

    public static void refreshPage() {
        Log.info("Refresh page");
        AqualityServices.getBrowser().refresh();
    }

    public static void goToPreviousPage() {
        Log.info("Go to previous page");
        AqualityServices.getBrowser().goBack();
    }

    public static void browserQuit() {
        AqualityServices.getBrowser().quit();
    }
}