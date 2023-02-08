package com.sicredi.cooperativismo.api.formatter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface DateFormat {
	
	String message() default "{}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String value() default "";

}
