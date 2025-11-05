package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository //yazmasak da çalışır. extend sayesinde anlıyor.
//Spring otomatik Impl sınıfını üretiyor.
public interface IndividualCustomerRepository extends CustomerRepository<IndividualCustomer> {
    //Doğrudan entity ile çalışıyoruz. Bu bizim için JPQL doğrudan entity ile ilişkili sorgu oluşturma yapısı.
    //Gidiyor entitynin içine bakıyor. addresses diye bir alan var mı. bunu map etmeye çalışıyor.
/*
    @Query("Select ic From IndividualCustomer ic left join fetch ic.addresses") //sorgunun içine address i dahil ediyorum
    List<IndividualCustomer> findAllWithAddresses();

    List<IndividualCustomer> findAllWithAddresses(); //address ile birlikte bana getir diyor
    //Direk spring arkaplanda bir sorgu üretiyor
    //Gidiyor Customerın içine addresses(Relationdaki isim) listesini buluyor. diyor ki sende böyle bir entity var ben buna göre sorgu üretirim
    */

    Optional<IndividualCustomer> findByLastName(String lastName); //optional ile aslında yoksa exception filan fırlatabiliyoruz

    //JPQL
    //Belirli bir isimle başlayan müşteriler
    @Query("Select ic From IndividualCustomer ic Where ic.firstName LIKE :prefix%") //: kullanma nedeni parametredki prefixi almasını salamak. bu hibernatin sağladığı bir şey
    List<IndividualCustomer> findByFirstNameStartingPrefix(@Param("prefix") String prefix);
    //bunu query ile yazıcam ama saf SQL değil hibernatin benim entitiydeki ilişkileri ile


    //native query direk SQL
    // Belli bir pattern ile customerNumber'a göre filtrele
    @Query(value = "Select * from individual_customers ic Join customers c on ic.customer_id = c.id Where c.customer_number like :pattern",nativeQuery = true)
    List<IndividualCustomer> findByCustomerNumberPattern(@Param("pattern") String pattern);

    boolean existsByNationalId(String identityNumber);

    Optional<IndividualCustomer> findById(UUID id);

    boolean existsById(UUID id);







}
