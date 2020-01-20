package com.blade.manager.system.enums;

/**
 * TODO:
 * 菜单的所有枚举
 * @author Blade
 * @date 2020/1/20 10:26
 */
public class MenuEnums {

    /**
     * 菜单类型枚举
     */
    public enum MenuTypeEnum {
        /**
         * 菜单类型枚举
         */
        CATALOG("catalog", "目录"),
        MENU("menu", "菜单"),
        BUTTON("button", "按钮"),
        ;

        private String code;
        private String msg;

        MenuTypeEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
