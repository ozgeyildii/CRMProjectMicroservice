package com.etiya.common.events;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

public record CreateAddressEvent(int id, String street,String houseNumber,String description, boolean isDefault, int districtId, UUID customerId){

}
