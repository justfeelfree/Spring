package com.firstfewlines.repository;

import com.firstfewlines.domain.WebService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Repository
public interface WebServiceJpaRepository extends JpaRepository<WebService, Integer> {
    List<WebService> findAllByNameAndPriceCondition();

    @Query("SELECT w FROM WebService w WHERE w.price >= 100")
    List<WebService> simpleQuery();

    @Query(value = "SELECT * FROM public.\"web_service\" WHERE name like '%13'", nativeQuery = true)
    List<WebService> nativeQuery();


}
