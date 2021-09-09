package com.ssuzalal.project.aop;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Constraint(validatedBy = PasswordFormValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordForm {
    String message() default "비밀번호는 영문, 숫자, 특수문자를 포함해 8글자 이상이어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
