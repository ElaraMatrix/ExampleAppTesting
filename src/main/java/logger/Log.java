package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {

    private Log() {}

    private static final Logger logger = LogManager.getLogger("FileLogger");

    public static void info(String text) {
        logger.info(text);
    }

    public static void warn(String text) {
        logger.warn(text);
    }

    public static void error(String text) {
        logger.error(text);
    }
}