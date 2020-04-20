package com.blade.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * BigDecimal工具类
 *
 * @author: Lism
 */
public class BigDecimalUtil {

    /**
     * 乘法
     *
     * @param multiplicand 被乘数
     * @param multiplier   乘数
     * @return
     */
    public static BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier) {
        return multiplicand.multiply(multiplier);
    }

    /**
     * 除法
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return dividend.divide(divisor, 3, BigDecimal.ROUND_DOWN);
    }

    /**
     * 除法
     * dividend.divide(divisor)
     * @param dividend 被除数
     * @param divisor  除数
     * @return {@link BigDecimal}
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int roundingMode) {
        return dividend.divide(divisor, 3, roundingMode);
    }

    /**
     * 减法
     *
     * @param subtracted  被减数
     * @param subtraction 减数
     * @return
     */
    public static BigDecimal subtract(BigDecimal subtracted, BigDecimal subtraction) {
        return subtracted.subtract(subtraction);
    }

    /**
     * 加法
     *
     * @param first  第一个参数
     * @param second 减数
     * @return
     */
    public static BigDecimal add(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }

    /**
     * 第一个是否比第二个小
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean smallerThan(BigDecimal first, BigDecimal second) {
        if (first == null) {
            return false;
        }
        if (second == null) {
            return false;
        }
        return first.compareTo(second) < 0;
    }

    /**
     * 第一个是否小与或者等于第二个
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean smallerThanOrEqual(BigDecimal first, BigDecimal second) {
        if (first == null) {
            return false;
        }
        if (second == null) {
            return false;
        }
        return first.compareTo(second) <= 0;
    }

    /**
     * 第一个是否比第二个大
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean biggerThan(BigDecimal first, BigDecimal second) {
        if (first == null) {
            return false;
        }
        if (second == null) {
            return false;
        }
        return first.compareTo(second) > 0;
    }

    /**
     * 第一个是否比第二个大
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean biggerThanEqual(BigDecimal first, BigDecimal second) {
        if (first == null) {
            return false;
        }
        if (second == null) {
            return false;
        }
        return first.compareTo(second) >= 0;
    }

    /**
     * 第一个是否和第二个相等
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean equal(BigDecimal first, BigDecimal second) {
        if (first == null) {
            return false;
        }
        if (second == null) {
            return false;
        }
        if (first.compareTo(second) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 四舍五入格式化,保留小数点后几位
     *
     * @param value
     * @return
     */
    public static BigDecimal formatHalfUp(BigDecimal value, int pointLatterBits) {
        if (value == null) {
            return null;
        }
        return value.setScale(pointLatterBits, RoundingMode.HALF_UP);
    }

    /**
     * 保留小数点后几位,后面的全部进1
     * <p>
     * 例如:5.342 保留两位小数结果为:5.35
     *
     * @param value
     * @param pointLatterBits
     * @return
     */
    public static BigDecimal formatAllUp(BigDecimal value, int pointLatterBits) {
        if (value == null) {
            return null;
        }
        return value.setScale(pointLatterBits, RoundingMode.UP);
    }

    /**
     * 保留小数点后几位,后面的全部舍去
     * <p>
     * 例如:5.3456 保留两位小数结果:5.34
     *
     * @param value
     * @param pointLatterBits
     * @return
     */
    public static BigDecimal formatAllDown(BigDecimal value, int pointLatterBits) {
        if (value == null) {
            return null;
        }
        return value.setScale(pointLatterBits, RoundingMode.DOWN);
    }


    /**
     * 金额格式化
     *
     * @param value   金额
     * @param pattern 格式
     * @return
     */
    public static BigDecimal format(BigDecimal value, String pattern) {
        if (value == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(value);
        return BigDecimal.valueOf(Double.parseDouble(format));
    }

    /**
     * 金额格式化
     *
     * @param value   金额
     * @param pattern 格式
     * @return
     */
    public static String formatForString(BigDecimal value, String pattern) {
        if (value == null) {
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(value);
    }

    /**
     * 比零大的金额
     *
     * @param bigDecimal
     * @return
     */
    public static Boolean biggerThanZero(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return false;
        }
        return bigDecimal.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * 比零小的金额
     *
     * @param bigDecimal
     * @return
     */
    public static Boolean smallerThanZero(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return false;
        }
        return bigDecimal.compareTo(BigDecimal.ZERO) < 0;
    }

    /**
     * 等于零的金额
     *
     * @param bigDecimal
     * @return
     */
    public static Boolean equalsZero(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return false;
        }
        return bigDecimal.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * 格式化成金额的类型。默认是 2位小数， 四舍五入
     *
     * @param fee fee
     * @return {@link BigDecimal}
     */
    public static BigDecimal format2FeeType(BigDecimal fee) {
        if (null == fee) {
            return new BigDecimal(0);
        }
        return fee.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
