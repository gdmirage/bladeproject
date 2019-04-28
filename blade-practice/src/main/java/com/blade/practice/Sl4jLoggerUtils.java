package com.blade.practice;

import org.slf4j.Logger;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/4/19 11:56
 */
public class Sl4jLoggerUtils {

    public static void trace(Logger logger, String msg, Object... objects) {
        if(logger.isTraceEnabled()) {
            logger.trace(msg, objects);
        }
    }

    public static void debug(Logger logger, String msg, Object... objects) {
        if(logger.isDebugEnabled()) {
            logger.debug(msg, objects);
        }
    }

    public static void info(Logger logger, String msg, Object... objects) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, objects);
        }
    }

    public static void warn(Logger logger, String msg, Object... objects) {
        if(logger.isWarnEnabled()) {
            logger.trace(msg, objects);
        }
    }

    public static void error(Logger logger, String msg, Object... objects) {
        if(logger.isErrorEnabled()) {
            logger.trace(msg, objects);
        }
    }
}
