package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.CharacteristicValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicValueRepository extends JpaRepository<CharacteristicValue,Integer> {
}
