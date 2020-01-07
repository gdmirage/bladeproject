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
         * 1 秒 转 毫秒
         */
        long ONE_SECOND_TO_MILLISECOND = 1000 * ONE_MILLISECOND;

        /**
         * 1 分钟 转 毫秒
         */
        long ONE_MINUTE_TO_MILLISECOND = 60 * ONE_SECOND_TO_MILLISECOND;

        /**
         * 1 小时 转 毫秒
         */
        long ONE_HOUR_TO_MILLISECOND = 60 * ONE_MINUTE_TO_MILLISECOND;

        /**
         * 1 天 转 毫秒
         */
        long ONE_DAY_TO_MILLISECOND = 24 * ONE_HOUR_TO_MILLISECOND;

        /**
         * 1 秒
         */
        long ONE_SECOND = 1;

        /**
         * 1 分钟 转 秒
         */
        long ONE_MINUTE_TO_SECOND = 60 * ONE_SECOND;

        /**
         * 1 小时 转 秒
         */
        long ONE_HOUR_TO_SECOND = 60 * ONE_MINUTE_TO_SECOND;

        /**
         * 1 天 转 秒
         */
        long ONE_DAY_TO_SECOND = 24 * ONE_HOUR_TO_SECOND;
    }

    /**
     * 缓存类常量
     */
    public interface Cache {
        /**
         * 验证码默认缓存时间
         */
        int CAPTCHA_EXPIRE_TIME = (int) (5 * Time.ONE_MINUTE_TO_SECOND);

        /**
         * token 默认缓存时间
         */
        int TOKEN_EXPIRE_TIME = (int) (2 * Time.ONE_HOUR_TO_SECOND);
    }
}
