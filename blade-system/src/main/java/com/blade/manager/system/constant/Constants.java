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
         * 1 秒 毫秒
         */
        long ONE_SECOND_TO_MILLISECOND = 1000 * ONE_MILLISECOND;

        /**
         * 1 分钟
         */
        long ONE_MINUTE_TO_MILLISECOND = 60 * ONE_SECOND_TO_MILLISECOND;

        /**
         * 1 小时
         */
        long ONE_HOUR_TO_MILLISECOND = 60 * ONE_MINUTE_TO_MILLISECOND;

        /**
         * 1 天
         */
        long ONE_DAY_TO_MILLISECOND = 24 * ONE_HOUR_TO_MILLISECOND;
    }

    /**
     * 缓存类常量
     */
    public interface Cache {
        /**
         * 验证码默认缓存时间
         */
        int CAPTCHA_EXPIRE_TIME = (int) (5 * Time.ONE_MINUTE_TO_MILLISECOND / 1000);

        /**
         * token 默认缓存时间
         */
        int TOKEN_EXPIRE_TIME = (int) (2 * Time.ONE_HOUR_TO_MILLISECOND / 1000);
    }
}
