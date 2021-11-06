package am.aca.specification.service.dto;

import am.aca.specification.entity.Applicant;
import am.aca.specification.validation.Age;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Validated
public class ApplicantDto {
    private Long id;
    private String name;

    @Email
    private String email;

    @NotNull
    private String phoneNumber;
    private String address;

    @Age(value = 10, message = "Age must be greater than 10")
    private int age;

    private CourseDto course;

    public static ApplicantDto mapEntityToDto(Applicant entity) {
        if (entity == null) {
            return null;
        }

        ApplicantDto dto = new ApplicantDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCourse(CourseDto.mapEntityToDto(entity.getCourse()));

        return dto;
    }

    public static List<ApplicantDto> mapEntitiesToDtos(List<Applicant> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }

        return entities.stream().map(ApplicantDto::mapEntityToDto).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
