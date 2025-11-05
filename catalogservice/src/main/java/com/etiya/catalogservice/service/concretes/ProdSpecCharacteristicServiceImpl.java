package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.ProdSpecCharacteristic;
import com.etiya.catalogservice.repository.ProdSpecCharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.ProdSpecCharacteristicService;
import com.etiya.catalogservice.service.dtos.requests.productspeccharacteristic.CreateProdSpecCharacteristicRequest;
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
    public CreatedProdSpecCharacteristicResponse add(CreateProdSpecCharacteristicRequest request) {
        ProdSpecCharacteristic entity = ProdSpecCharacteristicMapper.INSTANCE
                .prodSpecCharacteristicFromCreateProdSpecCharacteristicRequest(request);

        ProdSpecCharacteristic created = prodSpecCharacteristicRepository.save(entity);

        return ProdSpecCharacteristicMapper.INSTANCE.createdProdSpecCharacteristicResponseFromProdSpecCharacteristic(created);
    }
}
