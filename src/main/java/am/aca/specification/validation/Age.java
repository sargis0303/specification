package am.aca.specification.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Age {

    int value() default 18;

    String message() default "Age must be greater than 18";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
