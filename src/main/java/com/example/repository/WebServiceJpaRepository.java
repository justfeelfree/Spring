package com.example.repository;

import com.example.domain.WebService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Repository
public interface WebServiceJpaRepository extends JpaRepository<WebService, Integer> {
    List<WebService> findAllByNameEndsWith10();

    @Query("SELECT w FROM WebService w WHERE w.price >= 100")
    List<WebService> getByPriceGreaterOrEquals100();

    @Query(value = "SELECT * FROM public.\"web_service\" WHERE name like '%13'", nativeQuery = true)
    List<WebService> nativeQueryNameEndsWith13();

    @Query(value = "SELECT * FROM public.web_service WHERE json_data @> CONCAT('{', '\"price\":', :price, '}')", nativeQuery = true)
    List<WebService> nativeQueryPriceEquals100(@Param("price") BigDecimal price);
}
