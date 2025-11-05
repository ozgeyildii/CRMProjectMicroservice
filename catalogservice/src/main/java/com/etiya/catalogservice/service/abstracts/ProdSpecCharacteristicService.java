package com.etiya.catalogservice.service.abstracts;


import com.etiya.catalogservice.service.dtos.requests.productspeccharacteristic.CreateProdSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.productspeccharacteristic.CreatedProdSpecCharacteristicResponse;

public interface ProdSpecCharacteristicService {

    CreatedProdSpecCharacteristicResponse add(CreateProdSpecCharacteristicRequest request);
}
