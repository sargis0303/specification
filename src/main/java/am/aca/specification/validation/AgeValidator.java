package am.aca.specification.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Object> {
    private Integer age;

    @Override
    public void initialize(Age constraintAnnotation) {
        this.age = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return this.age < (Integer) value;
    }
}
