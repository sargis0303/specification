package am.aca.specification.exception;

import am.aca.specification.validation.ValidationError;
import lombok.Getter;

public class ViolationException extends RuntimeException{

    @Getter
    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }
}
