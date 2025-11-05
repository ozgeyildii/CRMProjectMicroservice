package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.ProdCharValue;
import com.etiya.catalogservice.repository.ProdCharValueRepository;
import com.etiya.catalogservice.service.abstracts.ProdCharValueService;
import com.etiya.catalogservice.service.dtos.requests.productcharvalue.CreateProdCharValueRequest;
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
    public CreatedProdCharValueResponse add(CreateProdCharValueRequest request) {
        ProdCharValue entity = ProdCharValueMapper.INSTANCE
                .prodCharValueFromCreateProdCharValueRequest(request);

        ProdCharValue created = prodCharValueRepository.save(entity);

        return ProdCharValueMapper.INSTANCE
                .createdProdCharValueResponseFromProdCharValue(created);
    }
}
