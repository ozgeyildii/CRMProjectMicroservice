package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.ContactMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMediumRepository extends JpaRepository<ContactMedium, Integer> {
    //JPQL Query
    @Query("Select cm From ContactMedium cm Where cm.value LIKE :value") //: kullanma nedeni parametredki prefixi almasını salamak. bu hibernatin sağladığı bir şey
    ContactMedium findByValue(@Param("value") String value);

    //Native Query
    @Query(value = "Select * from ContactMediums cm where cm.type = :type",nativeQuery = true)
    List<ContactMedium> findByType(@Param("type") String type);

    //Derived Query
    List<ContactMedium> findByCustomerId(int id);
}
