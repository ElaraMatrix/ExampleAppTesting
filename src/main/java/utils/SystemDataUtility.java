package utils;

import logger.Log;

public final class SystemDataUtility {

    private SystemDataUtility() {}

    public static String getThisEnvName() {
        Log.info("Get this environment name");
        return System.getenv().get("COMPUTERNAME");
    }
}