package com.example.searchservice.repository;

import com.example.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerSearchRepository extends ElasticsearchRepository<CustomerSearch, String>, CustomCustomerSearchRepository {

    Optional<CustomerSearch> findById(String id);

    @Query("""
            {
              "bool": {
                "must": [
                  { "term": { "nationalId.keyword": "?0" } }
                ]
              }
            }
            """)
    List<CustomerSearch> findByNationalIdExact(String nationalId);


    @Query("""
            {
            "query_string":{
            "query" : "*?0*",
            "fields":["firstName","lastName","nationalId","customerNumber"]
            }
            }
            """)
    List<CustomerSearch> searchAllFields(String keyword);

    @Query("""
            {
            "match":{
               "firstName":"?0"
                }
            }
            """)
    List<CustomerSearch> findByFirstNameMatch(String name);

    @Query("""
            {
            "term":{
               "nationalId.keyword":{
                    "value":"?0"
                    }
                }
            }
            """)
    List<CustomerSearch> findByExactValue(String nationalId);

    @Query("""
            {
            "fuzzy":{
                    "firstName":{
                        "value":"?0",
                        "fuzziness":2
                     }
                }
            }
            """)
    List<CustomerSearch> findBySimilarName(String name);

    @Query("""
            {
            "range":{
                "dateOfBirth":{
                    "gte":"?0",
                    "lte":"?1"
                    }
                }
            }
            """)
    List<CustomerSearch> findByDateRange(String start, String end);


    @Query("""
            {
                "bool": {
                "must": [
                     { "match": { "firstName": "?0" }},
                     { "match": { "gender": "?1" }}
                        ]
                 }
            }
            """)
    List<CustomerSearch> findFirstNameAndGender(String firstName, String gender);


}




