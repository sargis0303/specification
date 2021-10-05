package am.aca.specification.controller;

import am.aca.specification.service.ApplicantService;
import am.aca.specification.service.dto.ApplicantDto;
import am.aca.specification.service.model.ApplicantSearchCriteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/search")
    public List<ApplicantDto> searchApplicants(ApplicantSearchCriteria criteria) {
        return applicantService.search(criteria);
    }

    @GetMapping("/search/count")
    public Long searchApplicantsCount(ApplicantSearchCriteria criteria) {
        return applicantService.count(criteria);
    }
}
