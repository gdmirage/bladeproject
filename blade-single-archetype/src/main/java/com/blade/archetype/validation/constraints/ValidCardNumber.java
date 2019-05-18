package com.blade.archetype.validation.constraints;

import com.blade.archetype.validation.ValidCardNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO:
 * 卡号校验
 * 前面是以BLADE开头，"-"分开，后面是数字
 *
 * @author Blade
 * @date 2019/5/2 22:13
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidCardNumberConstraintValidator.class})
public @interface ValidCardNumber {
    String message() default "{com.blade.validation.constraints.card.number.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
