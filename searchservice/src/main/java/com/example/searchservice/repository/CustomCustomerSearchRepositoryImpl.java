package com.example.searchservice.repository;

import com.example.searchservice.domain.CustomerSearch;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import java.text.Normalizer;


import java.util.List;
import java.util.Locale;

@Repository
public class CustomCustomerSearchRepositoryImpl implements CustomCustomerSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    public CustomCustomerSearchRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }
    @Override
    public List<CustomerSearch> searchDynamic(
            String id,
            String customerNumber,
            String accountNumber,
            String nationalId,
            String firstName,
            String lastName,
            String value,
            int page,
            int size
    ) {
        BoolQuery.Builder bool = QueryBuilders.bool();

        if (StringUtils.hasText(id)) {
            bool.must(m -> m.term(t -> t.field("id.keyword").value(id)));
        }

        if (StringUtils.hasText(customerNumber)) {
            bool.must(m -> m.term(t -> t.field("customerNumber.keyword").value(customerNumber)));
        }

        if (StringUtils.hasText(nationalId)) {
            bool.must(m -> m.term(t -> t.field("nationalId.keyword").value(nationalId)));
        }

        if (StringUtils.hasText(firstName)) {
            String originalLower = firstName.toLowerCase(Locale.ROOT);
            String normalized = Normalizer.normalize(firstName, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase(Locale.ROOT)
                    .replace("ı", "i")
                    .replace("ö", "o")
                    .replace("ü", "u")
                    .replace("ş", "s")
                    .replace("ğ", "g")
                    .replace("ç", "c");

            Query originalQuery = Query.of(q -> q
                    .wildcard(w -> w
                            .field("firstName")
                            .caseInsensitive(true)
                            .value("*" + originalLower + "*")
                    )
            );

            Query normalizedQuery = Query.of(q -> q
                    .wildcard(w -> w
                            .field("firstName")
                            .caseInsensitive(true)
                            .value("*" + normalized + "*")
                    )
            );

            List<Query> shouldQueries = List.of(originalQuery, normalizedQuery);

            bool.must(m -> m.bool(b -> b.should(shouldQueries)));
        }

        if (StringUtils.hasText(lastName)) {
            String lowerLastName = lastName.toLowerCase();
            bool.must(m -> m.wildcard(w -> w
                    .field("lastName.keyword")
                    .caseInsensitive(true)
                    .value("*" + lowerLastName + "*")
            ));
        }

        if (StringUtils.hasText(accountNumber)) {
            String upperAccountNumber = accountNumber.toUpperCase();
            bool.must(m -> m
                    .nested(n -> n
                            .path("billingAccounts")
                            .query(q -> q
                                    .term(t -> t
                                            .field("billingAccounts.accountNumber")
                                            .value(upperAccountNumber)
                                    )
                            )
                    )
            );
        }

        if (StringUtils.hasText(value)) {
            bool.must(m -> m.nested(n -> n
                    .path("contactMediums")
                    .query(q -> q.bool(nb -> nb
                            .must(mt -> mt.term(t -> t.field("contactMediums.type.keyword").value("PHONE")))
                            .must(mt -> mt.term(t -> t.field("contactMediums.value.keyword").value(value)))
                    ))
            ));
        }

        BoolQuery builtBool = bool.build();

        Query query;
        if (builtBool.must().isEmpty()) {
            query = QueryBuilders.matchAll().build()._toQuery();
        } else {
            query = builtBool._toQuery();
        }

        Pageable pageable = PageRequest.of(page, size);

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .withPageable(pageable)
                .build();

        SearchHits<CustomerSearch> hits = elasticsearchOperations.search(nativeQuery, CustomerSearch.class);

        return hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
    }

}