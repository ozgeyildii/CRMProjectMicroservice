package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    // 1️ JPQL Query
    @Query("SELECT a FROM Address a WHERE a.isDefault = true")
    List<Address> findDefaultAddresses();

    // 2️ Native Query
    @Query(value = "SELECT * FROM addresses WHERE description LIKE %:keyword%", nativeQuery = true)
    List<Address> findByDescriptionNative(@Param("keyword") String keyword);

    // 3️ Derived Query (hazır method)
    List<Address> findByStreetContaining(String streetPart);

    void deleteById(int id);

    boolean existsByCustomerId(UUID customerId);
}
