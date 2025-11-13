package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.ProdOfferCharValue;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdOfferCharValueRepository extends JpaRepository<ProdOfferCharValue,Integer> {
    @Query("SELECT pcv FROM ProdOfferCharValue pcv " +
            "JOIN FETCH pcv.productOffer po " +
            "JOIN FETCH pcv.characteristicValue cv " +
            "JOIN FETCH cv.characteristic c " +
            "WHERE po.id IN :productOfferIds")
    List<ProdOfferCharValue> findAllByProductOfferIds(@Param("productOfferIds") List<Integer> productOfferIds);
}
