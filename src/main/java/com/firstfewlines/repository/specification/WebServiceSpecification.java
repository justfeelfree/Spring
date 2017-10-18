package com.firstfewlines.repository.specification;

import com.firstfewlines.domain.WebService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

/**
 * @author Kirill Nigmatullin
 */
public class WebServiceSpecification {

/*    public static Specification<WebService> webServicePriceLT500(final BigDecimal price) {
        return new Specification<WebService>() {
            @Override
            public Predicate toPredicate(Root<WebService> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("owner").get("lastName"), lastName);
            }
        };
    }*/

    public static Specification<WebService> nameEndsWith(final String Name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + "11");
    }
}
