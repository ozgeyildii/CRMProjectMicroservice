package com.etiya.catalogservice.service.dtos.requests.productoffercharvalue;

import java.util.List;

public class GetCharacteristicsByProductOffersRequest {
    private List<Integer> productOfferIds;

    public List<Integer> getProductOfferIds() {
        return productOfferIds;
    }

    public void setProductOfferIds(List<Integer> productOfferIds) {
        this.productOfferIds = productOfferIds;
    }


}
