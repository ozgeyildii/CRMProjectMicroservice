package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicRepository extends JpaRepository<Characteristic,Integer> {
}
