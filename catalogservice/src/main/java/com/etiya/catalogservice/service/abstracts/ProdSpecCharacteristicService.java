package com.etiya.catalogservice.service.abstracts;


import com.etiya.catalogservice.service.dtos.requests.productofferspeccharacteristic.CreateProdOfferSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.productspeccharacteristic.CreatedProdSpecCharacteristicResponse;

public interface ProdSpecCharacteristicService {

    CreatedProdSpecCharacteristicResponse add(CreateProdOfferSpecCharacteristicRequest request);
}
