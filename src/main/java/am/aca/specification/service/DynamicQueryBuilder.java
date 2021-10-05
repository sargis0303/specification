package am.aca.specification.service;

import am.aca.specification.entity.Applicant;
import am.aca.specification.service.model.ApplicantSearchCriteria;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public final class DynamicQueryBuilder {

    private static final String ATTRIBUTE_PATH_NAME = "name";
    private static final String ATTRIBUTE_PATH_EMAIL = "email";
    private static final String ATTRIBUTE_PATH_COURSE = "course";
    private static final String ATTRIBUTE_PATH_ID = "id";

    private DynamicQueryBuilder() {
    }

    public static Specification<Applicant> findApplicant(ApplicantSearchCriteria criteria) {
        return ((root, query, criteriaBuilder) -> getFindApplicantPredicate(root, criteriaBuilder, criteria));
    }

    private static Predicate getFindApplicantPredicate(Root<Applicant> root, CriteriaBuilder cb, ApplicantSearchCriteria criteria) {
        List<Predicate> predicates = new ArrayList<>();

        // name condition
        if (!Strings.isBlank(criteria.getName())) {
            predicates.add(cb.like(cb.lower(root.get(ATTRIBUTE_PATH_NAME)),
                    cb.lower(cb.literal("%" + criteria.getName().trim() + "%"))));
        }

        // email condition
        if (!Strings.isBlank(criteria.getEmail())) {
            Path<String> namePath = root.get(ATTRIBUTE_PATH_EMAIL);
            predicates.add(cb.like(cb.lower(namePath),
                    cb.lower(cb.literal("%" + criteria.getEmail().trim() + "%"))));
        }

        // course condition
        if (criteria.getCourseId() != null) {
            predicates.add(cb.equal(root.get(ATTRIBUTE_PATH_COURSE).get(ATTRIBUTE_PATH_ID), criteria.getCourseId()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
