package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Characteristic;
import com.etiya.catalogservice.domain.CharacteristicValue;
import com.etiya.catalogservice.domain.ProdOfferCharValue;
import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.repository.ProdOfferCharValueRepository;
import com.etiya.catalogservice.service.abstracts.ProdOfferCharValueService;
import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.CreateProdOfferCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.GetCharacteristicsByProductOffersRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.GetCharacteristicsByProductOffersResponse;
import com.etiya.catalogservice.service.mappings.ProdCharValueMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdOfferCharValueServiceImpl implements ProdOfferCharValueService {

    private final ProdOfferCharValueRepository prodOfferCharValueRepository;

    public ProdOfferCharValueServiceImpl(ProdOfferCharValueRepository prodOfferCharValueRepository) {
        this.prodOfferCharValueRepository = prodOfferCharValueRepository;
    }

    @Override
    public CreatedProdCharValueResponse add(CreateProdOfferCharValueRequest request) {
        ProdOfferCharValue entity = ProdCharValueMapper.INSTANCE
                .prodCharValueFromCreateProdCharValueRequest(request);

        ProdOfferCharValue created = prodOfferCharValueRepository.save(entity);

        return ProdCharValueMapper.INSTANCE
                .createdProdCharValueResponseFromProdCharValue(created);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetCharacteristicsByProductOffersResponse> getCharacteristicsByProductOffers(GetCharacteristicsByProductOffersRequest request) {

        // 🔹 Product Offer ID listesine göre ilişkileri çek
        List<ProdOfferCharValue> relations =
                prodOfferCharValueRepository.findAllByProductOfferIds(request.getProductOfferIds());

        // 🔹 Offer bazında grupla
        Map<Integer, GetCharacteristicsByProductOffersResponse> offerMap = new HashMap<>();

        for (ProdOfferCharValue relation : relations) {
            ProductOffer offer = relation.getProductOffer();
            CharacteristicValue cv = relation.getCharacteristicValue();
            Characteristic c = cv.getCharacteristic();

            // Her offer için bir DTO oluştur
            GetCharacteristicsByProductOffersResponse offerDto = offerMap.computeIfAbsent(
                    offer.getId(),
                    id -> {
                        GetCharacteristicsByProductOffersResponse dto = new GetCharacteristicsByProductOffersResponse();
                        dto.setProductOfferId(offer.getId());
                        dto.setProductOfferName(offer.getName());
                        dto.setCharacteristics(new ArrayList<>());
                        return dto;
                    });

            // Karakteristik DTO’sunu oluştur
            GetCharacteristicsByProductOffersResponse.CharacteristicDto charDto =
                    new GetCharacteristicsByProductOffersResponse.CharacteristicDto();
            charDto.setId(c.getId());
            charDto.setName(c.getName());
            charDto.setEditable(c.isEditable());

            // Değerleri oku
            List<String> values = c.getCharacteristicValues()
                    .stream()
                    .map(CharacteristicValue::getValue)
                    .toList();

            charDto.setValues(values);
            offerDto.getCharacteristics().add(charDto);
        }

        // 🔹 Liste olarak döndür
        return new ArrayList<>(offerMap.values());
    }
}