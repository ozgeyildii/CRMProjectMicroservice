package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.ProdOfferSpecCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdSpecCharacteristicRepository extends JpaRepository<ProdOfferSpecCharacteristic,Integer> {
}
