package com.etiya.catalogservice.service.abstracts;


import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.CreateProdOfferCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.GetCharacteristicsByProductOffersRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.GetCharacteristicsByProductOffersResponse;

import java.util.List;

public interface ProdOfferCharValueService {

    CreatedProdCharValueResponse add(CreateProdOfferCharValueRequest request);

    List<GetCharacteristicsByProductOffersResponse> getCharacteristicsByProductOffers(GetCharacteristicsByProductOffersRequest request);
}
