package com.blade.manager.system.constant;

/**
 * TODO:
 * 常量
 *
 * @author Blade
 * @date 2020/1/4 10:00
 */
public class Constants {

    public interface Time {
        /**
         * 1 毫秒
         */
        long ONE_MILLISECOND = 1;

        /**
         * 1 秒
         */
        long ONE_SECOND = 1000 * ONE_MILLISECOND;

        /**
         * 1 分钟
         */
        long ONE_MINUTE = 60 * ONE_SECOND;

        /**
         * 1 小时
         */
        long ONE_HOUR = 60 * ONE_MINUTE;

        /**
         * 1 天
         */
        long ONE_DAY = 24 * ONE_HOUR;
    }

    /**
     * 缓存类常量
     */
    public interface Cache {
        /**
         * 验证码默认缓存时间
         */
        int CAPTCHA_EXPIRE_TIME = (int) (5 * Time.ONE_MINUTE);

        /**
         * token 默认缓存时间
         */
        int TOKEN_EXPIRE_TIME = (int) (2 * Time.ONE_HOUR);
    }
}
