package com.example.repository.specification;

import com.example.domain.WebService;
import org.springframework.data.jpa.domain.Specification;

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
