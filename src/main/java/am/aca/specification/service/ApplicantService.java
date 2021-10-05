package am.aca.specification.service;

import am.aca.specification.entity.Applicant;
import am.aca.specification.repository.ApplicantRepository;
import am.aca.specification.service.dto.ApplicantDto;
import am.aca.specification.service.model.ApplicantSearchCriteria;
import am.aca.specification.service.model.LimitOffsetPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public List<ApplicantDto> search(ApplicantSearchCriteria criteria) {
        Specification<Applicant> specification = getApplicantSearchSpecification(criteria);

        Pageable pageRequest = new LimitOffsetPageRequest(criteria.getLimit(), criteria.getOffset());

        Page<Applicant> applicants = applicantRepository.findAll(specification, pageRequest);

        return ApplicantDto.mapEntitiesToDtos(applicants.getContent());
    }

    public Long count(ApplicantSearchCriteria criteria) {
        return applicantRepository.count(DynamicQueryBuilder.findApplicant(criteria));
    }

    private Specification<Applicant> getApplicantSearchSpecification(ApplicantSearchCriteria criteria) {
        return DynamicQueryBuilder.findApplicant(criteria);
    }

}
