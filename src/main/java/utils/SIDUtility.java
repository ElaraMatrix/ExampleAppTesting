package utils;

import logger.Log;

import java.util.Date;

public class SIDUtility {

    private static String SID = null;

    private SIDUtility() {}

    public static String getSID() {
        Log.info("Get SID");
        if (SID == null) {
            SID = String.valueOf(new Date().getTime());
        }
        return SID;
    }
}