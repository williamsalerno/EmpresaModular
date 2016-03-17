package br.com.contmatic.empresawilliam.util;

import java.util.Set;

import javax.validation.*;

/**
 * The Class ValidationUtil.
 *
 * @author williansalerno
 */
public final class ValidationUtil {

    /**
     * Instantiates a new validation util.
     */
    private ValidationUtil() {

    }

    /**
     * Has errors.
     *
     * @param obj the obj
     * @param message the message
     * @return true, if successful
     */
    public static boolean hasErrors(Object obj, String message) {
        if (message != null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> errors = validator.validate(obj);
            for(ConstraintViolation<Object> constraintViolation : errors) {
                if (message.equals(constraintViolation.getMessage())) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Has errors.
     *
     * @param obj the obj
     * @param message the message
     * @param groups the groups
     * @return true, if successful
     */
    public static boolean hasErrors(Object obj, String message, Class<?>... groups) {
        if (message != null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> errors = validator.validate(obj, groups);
            for(ConstraintViolation<Object> constraintViolation : errors) {
                if (message.equals(constraintViolation.getMessage())) {
                    return true;
                }
            }
        }
        return false;

    }
    
    public static boolean hasErrors(Object obj) {
        if (obj != null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> errors = validator.validate(obj);
            if(errors.size() > 0){
                return true;
            }
        }
        return false;

    }

}
