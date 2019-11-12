package com.kjtpay.util;


import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ValidationUtil {

	private static Validator validator ;
	private String fastFalse ;
	public ValidationUtil(){
		
	}
	
	public <T> ValidationResult validate(T obj) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		if (CollectionUtils.isNotEmpty(set)) {
			result.setError(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
			result.setMessage(errorMsg);
		}
		return result;
	}
	
	public <T> ValidationResult validate(T obj,Class<?> group) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validate(obj, group);
		if (CollectionUtils.isNotEmpty(set)) {
			result.setError(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
			result.setMessage(errorMsg);
		}
		return result;
	}

	

	
	public void init(){
		validator = Validation.byProvider(HibernateValidator.class)
				.configure().addProperty(HibernateValidatorConfiguration.FAIL_FAST, fastFalse).buildValidatorFactory().getValidator();
	}
	
	public String getFastFalse() {
		return fastFalse;
	}
	
	public void setFastFalse(String fastFalse) {
		this.fastFalse = fastFalse;
	}
}