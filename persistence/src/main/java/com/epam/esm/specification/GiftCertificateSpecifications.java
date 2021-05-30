package com.epam.esm.specification;

import com.epam.esm.domain.GiftCertificate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates dynamic query to database.
 */
public class GiftCertificateSpecifications implements Specification<GiftCertificate> {

    private List<SearchCriteria> list;

    /**
     * Instantiates a new Gift certificate specifications.
     */
    public GiftCertificateSpecifications() {
        this.list = new ArrayList<>();
    }

    /**
     * Add.
     *
     * @param criteria the criteria
     */
    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<GiftCertificate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.LIKE)) {
                String containsPattern = getContainsLikePattern(criteria.getValue().toString());
                predicates.add(criteriaBuilder.like(root.get(criteria.getKey()), containsPattern));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }

}
