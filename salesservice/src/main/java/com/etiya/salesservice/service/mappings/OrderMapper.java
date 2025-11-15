package com.etiya.salesservice.service.mappings;

import com.etiya.common.responses.GetBasketItemResponse;
import com.etiya.common.responses.GetBasketResponse;
import com.etiya.salesservice.domain.entities.Order;
import com.etiya.salesservice.domain.entities.OrderItem;
import com.etiya.salesservice.domain.entities.OrderItemCharValue;
import com.etiya.salesservice.service.dtos.requests.CreateOrderItemCharValueRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderItemCharValueResponse;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderItemResponse;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "billingAccountId", source = "billingAccId")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "orderItems", source = "basketItems")
    Order orderFromGetBasketResponse(GetBasketResponse basket);


    @Mapping(target = "basketItemId", source = "basketItemId")
    @Mapping(target = "productOfferId", source = "productOfferId")
    @Mapping(target = "productOfferName", source = "productOfferName")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "discountRate", source = "discountRate")
    @Mapping(target = "discountedPrice", source = "discountedPrice")
    @Mapping(target = "sourceType", source = "type") // 🔥 DOĞRU ALAN
    @Mapping(target = "orderItemCharValues", ignore = true) // 🔥 DOĞRU ALAN
    OrderItem orderItemFromGetBasketItemResponse(GetBasketItemResponse item);


    List<OrderItem> orderItemListFromGetBasketItemResponseList(List<GetBasketItemResponse> items);


    @Mapping(target = "characteristicName", source = "characteristicName")
    @Mapping(target = "characteristicValue", source = "characteristicValue")
    OrderItemCharValue orderItemCharValueFromCreateRequest(CreateOrderItemCharValueRequest req);




    List<OrderItemCharValue> orderItemCharValueListFromCreateRequestList(
            List<CreateOrderItemCharValueRequest> reqs
    );


    @Mapping(target = "createdOrderItem", source = "orderItems")
    CreatedOrderResponse createdOrderResponseFromOrder(Order order);


    @Mapping(target = "createdOrderItemCharValues", source = "orderItemCharValues") // 🔥 DOĞRU EŞLEŞME
    CreatedOrderItemResponse createdOrderItemResponseFromOrderItem(OrderItem item);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderItemId", source = "orderItemId")
    @Mapping(target = "characteristicName", source = "characteristicName")
    @Mapping(target = "characteristicValue", source = "characteristicValue")
    CreatedOrderItemCharValueResponse createdOrderItemCharValueResponseFromOrderItemCharValue(
            OrderItemCharValue value
    );

    List<CreatedOrderItemCharValueResponse> createdOrderItemCharValueResponseListFrom(
            List<OrderItemCharValue> values
    );
}

