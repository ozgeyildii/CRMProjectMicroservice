package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.ProdOfferCharValue;
import com.etiya.catalogservice.repository.ProdCharValueRepository;
import com.etiya.catalogservice.service.abstracts.ProdCharValueService;
import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.CreateProdOfferCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.mappings.ProdCharValueMapper;
import org.springframework.stereotype.Service;

@Service
public class ProdCharValueServiceImpl implements ProdCharValueService {

    private final ProdCharValueRepository prodCharValueRepository;

    public ProdCharValueServiceImpl(ProdCharValueRepository prodCharValueRepository) {
        this.prodCharValueRepository = prodCharValueRepository;
    }

    @Override
    public CreatedProdCharValueResponse add(CreateProdOfferCharValueRequest request) {
        ProdOfferCharValue entity = ProdCharValueMapper.INSTANCE
                .prodCharValueFromCreateProdCharValueRequest(request);

        ProdOfferCharValue created = prodCharValueRepository.save(entity);

        return ProdCharValueMapper.INSTANCE
                .createdProdCharValueResponseFromProdCharValue(created);
    }
}
