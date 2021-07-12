package com.epam.esm.specification;

import com.epam.esm.domain.GiftCertificate;
import org.springframework.data.jpa.domain.Specification;

/**
 * Creates SearchCriteria from parameters and build specification
 */
public class SpecificationBuilder {

    /**
     * Private constructor for utility class
     */
    private SpecificationBuilder() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * Build specification.
     *
     * @param name        the name
     * @param description the description
     * @return the specification
     */
    public static Specification<GiftCertificate> build(String name, String description) {
        GiftCertificateSpecifications specifications = new GiftCertificateSpecifications();
        if (name != null && !name.isEmpty()) {
            SearchCriteria criteria1 = new SearchCriteria("name", name, SearchOperation.LIKE);
            specifications.add(criteria1);
        }
        if (description != null && !description.isEmpty()) {
            SearchCriteria criteria2 = new SearchCriteria("description", description, SearchOperation.LIKE);
            specifications.add(criteria2);
        }
        return specifications;
    }

}
