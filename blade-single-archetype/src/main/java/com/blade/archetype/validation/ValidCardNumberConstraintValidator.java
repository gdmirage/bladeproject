package com.blade.archetype.validation;

import com.blade.archetype.validation.constraints.ValidCardNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * TODO:
 * {@link ValidCardNumber}  {@link ConstraintValidator} 实现
 * <p>
 * 卡号校验的实现类
 *
 * @author Blade
 * @date 2019/5/2 22:23
 */
public class ValidCardNumberConstraintValidator
        implements ConstraintValidator<ValidCardNumber, String> {
    @Override
    public void initialize(ValidCardNumber constraintAnnotation) {

    }

    /**
     * 前面是以 "BLADE" 开头，"-" 分开，后面是数字
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String[] parts = StringUtils.split(value, "-");
        // 为什么不使用String#split 方法，原因是该方法使用了正则表达式。而且NPE保护不够
        if (ArrayUtils.getLength(parts) != 2) {
            return false;
        }

        String prefix = parts[0];
        String suffix = parts[1];

        boolean isValidPrefix = Objects.equals(prefix, "BLADE");

        boolean isValidInteger = StringUtils.isNumeric(suffix);

        return isValidPrefix && isValidInteger;
    }
}
