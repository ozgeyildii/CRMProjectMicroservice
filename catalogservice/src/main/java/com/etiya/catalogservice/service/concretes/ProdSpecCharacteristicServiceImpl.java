package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.ProdOfferSpecCharacteristic;
import com.etiya.catalogservice.repository.ProdSpecCharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.ProdSpecCharacteristicService;
import com.etiya.catalogservice.service.dtos.requests.productofferspeccharacteristic.CreateProdOfferSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.productspeccharacteristic.CreatedProdSpecCharacteristicResponse;
import com.etiya.catalogservice.service.mappings.ProdSpecCharacteristicMapper;
import org.springframework.stereotype.Service;

@Service
public class ProdSpecCharacteristicServiceImpl implements ProdSpecCharacteristicService {

    private final ProdSpecCharacteristicRepository prodSpecCharacteristicRepository;

    public ProdSpecCharacteristicServiceImpl(ProdSpecCharacteristicRepository prodSpecCharacteristicRepository) {
        this.prodSpecCharacteristicRepository = prodSpecCharacteristicRepository;
    }

    @Override
    public CreatedProdSpecCharacteristicResponse add(CreateProdOfferSpecCharacteristicRequest request) {
        ProdOfferSpecCharacteristic entity = ProdSpecCharacteristicMapper.INSTANCE
                .prodSpecCharacteristicFromCreateProdSpecCharacteristicRequest(request);

        ProdOfferSpecCharacteristic created = prodSpecCharacteristicRepository.save(entity);

        return ProdSpecCharacteristicMapper.INSTANCE.createdProdSpecCharacteristicResponseFromProdSpecCharacteristic(created);
    }
}
