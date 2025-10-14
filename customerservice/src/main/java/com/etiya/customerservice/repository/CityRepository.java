package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    //JPQL
    @Query("Select c from City c where c.createdDate > :parameter")
    List<City> findByCreatedDateBiggerThanParameter(@Param("parameter") LocalDateTime parameter);

    //Native Query
    @Query(value = "Select * from City c where c.created_date > parameter", nativeQuery = true)
    List<City> findByCreatedDateBiggerThanParameterNative(@Param("parameter") LocalDateTime parameter);

    //Derived Query (Spring Data JPA metodu)
    List<City> findByCreatedDate(LocalDateTime createdDate);

    boolean existsByNameIgnoreCase(String name);

    boolean existsById(int id);


}
