package am.aca.specification.controller;

import am.aca.specification.service.ApplicantService;
import am.aca.specification.service.dto.ApplicantDto;
import am.aca.specification.service.model.ApplicantSearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    private final Logger log = LoggerFactory.getLogger(ApplicantController.class);

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/logs")
    public void testLogs() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
    }

    @PostMapping
    public ApplicantDto create(@RequestBody @Valid ApplicantDto applicantDto) {
        return applicantService.create(applicantDto);
    }

    @GetMapping
    public void throwException() {
        throw new IllegalArgumentException("Exception in controller");
    }

    @GetMapping("/{id}")
    public ApplicantDto findById(@PathVariable Long id) {
        return applicantService.findById(id);
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
