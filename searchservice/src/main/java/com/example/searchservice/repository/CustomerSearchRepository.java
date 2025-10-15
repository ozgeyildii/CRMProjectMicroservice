package com.example.searchservice.repository;

import com.example.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSearchRepository extends ElasticsearchRepository<CustomerSearch,String> {

}