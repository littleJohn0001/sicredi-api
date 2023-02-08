package com.sicredi.cooperativismo.api.formatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isNoneBlank(value)) {
//			return 
		}
		return false;
	}

}
