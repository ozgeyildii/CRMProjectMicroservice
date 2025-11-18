package com.example.searchservice.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.example.searchservice.domain.CustomerSearch;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

        addExactTerm(bool, "id.keyword", id);
        addExactTerm(bool, "customerNumber.keyword", customerNumber);
        addExactTerm(bool, "nationalId.keyword", nationalId);

        addWildcardForFirstName(bool, firstName);
        addWildcardForLastName(bool, lastName);

        addNestedTerm(bool, "billingAccounts", "billingAccounts.accountNumber", accountNumber);
        addPhoneValue(bool, value);

        Query finalQuery = buildFinalQuery(bool);
        Pageable pageable = PageRequest.of(page, size);

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(finalQuery)
                .withPageable(pageable)
                .build();

        SearchHits<CustomerSearch> hits = elasticsearchOperations.search(nativeQuery, CustomerSearch.class);

        return hits.getSearchHits().stream().map(SearchHit::getContent).toList();
    }

    private void addExactTerm(BoolQuery.Builder bool, String field, String value) {
        if (StringUtils.hasText(value)) {
            bool.must(m -> m.term(t -> t.field(field).value(value)));
        }
    }

    private void addWildcardForFirstName(BoolQuery.Builder bool, String value) {
        if (!StringUtils.hasText(value)) return;

        String original = value.toLowerCase(Locale.ROOT);
        String normalized = normalize(value);

        bool.must(m -> m.bool(b -> b.should(
                List.of(
                        wildcard("firstName", original),
                        wildcard("firstName", normalized)
                )
        )));
    }

    private void addWildcardForLastName(BoolQuery.Builder bool, String value) {
        if (!StringUtils.hasText(value)) return;

        String original = value.toLowerCase(Locale.ROOT);
        String normalized = normalize(value);

        bool.must(m -> m.bool(b -> b.should(
                List.of(
                        wildcard("lastName", original),
                        wildcard("lastName", normalized)
                )
        )));
    }

    private Query wildcard(String field, String value) {
        return Query.of(q -> q.wildcard(w -> w
                .field(field)
                .caseInsensitive(true)
                .value("*" + value + "*")));
    }

    private void addNestedTerm(BoolQuery.Builder bool, String path, String field, String value) {
        if (!StringUtils.hasText(value)) return;

        String processed = value.toUpperCase(Locale.ROOT);

        bool.must(m -> m.nested(n -> n
                .path(path)
                .query(q -> q.term(t -> t.field(field).value(processed)))
        ));
    }

    private void addPhoneValue(BoolQuery.Builder bool, String value) {
        if (!StringUtils.hasText(value)) return;

        bool.must(m -> m.nested(n -> n
                .path("contactMediums")
                .query(q -> q.bool(nb -> nb
                        .must(mt -> mt.term(t -> t.field("contactMediums.type.keyword").value("PHONE")))
                        .must(mt -> mt.term(t -> t.field("contactMediums.value.keyword").value(value)))
                ))
        ));
    }

    private Query buildFinalQuery(BoolQuery.Builder bool) {
        BoolQuery built = bool.build();
        return built.must().isEmpty()
                ? QueryBuilders.matchAll().build()._toQuery()
                : built._toQuery();
    }

    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .replace("ı", "i")
                .replace("ö", "o")
                .replace("ü", "u")
                .replace("ş", "s")
                .replace("ğ", "g")
                .replace("ç", "c");
    }
}
