package utils;

import aquality.selenium.browser.AqualityServices;
import logger.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public final class KeyboardUtils {

    private KeyboardUtils() {}

    private static final Actions actionProvider = new Actions(AqualityServices.getBrowser().getDriver());

    public static void keyESC() {
        Log.info("press on ESCAPE");
        actionProvider.sendKeys(Keys.ESCAPE).build().perform();
    }
}