package com.etiya.salesservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "products")
public class Product {

    @MongoId
    @Field(name = "id")
    private String id= UUID.randomUUID().toString();

    @Field(name = "billingAccountId")
    private int billingAccountId;

    @Field(name="productOfferId")
    private int productOfferId;

    @Field(name = "productOfferName")
    private String productOfferName;

    @Field(name="addressId")
    private int addressId;

//    public class ProductConfiguration {
//        private String key;   // Örn: "XDSL No"
//        private String value; // Örn: "12345678"
//    }


}
