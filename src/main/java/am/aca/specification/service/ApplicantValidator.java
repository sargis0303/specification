package am.aca.specification.service;

import am.aca.specification.exception.ViolationException;
import am.aca.specification.service.dto.ApplicantDto;
import am.aca.specification.validation.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicantValidator {

    public void validate(ApplicantDto applicantDto) {
        ValidationError error = new ValidationError();

//        if (applicantDto.getAge() < 18) {
//            error.addViolation("age", "Age must be greater than 18");
//        }

        if (applicantDto.getName().contains("INVALID")) {
            log.warn("Exception occurred during applicant validation => field: {}, value {}", "name", applicantDto.getName());
            error.addViolation("name", "Name must not contain INVALID ");
        }

        if (error.hasViolation()) {
            throw new ViolationException(error);
        }
    }

}
