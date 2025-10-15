package com.example.searchservice.service;

import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerSearchServiceImpl implements CustomerSearchService{

    private final CustomerSearchRepository customerSearchRepository;

    public CustomerSearchServiceImpl(CustomerSearchRepository customerSearchRepository) {
        this.customerSearchRepository = customerSearchRepository;
    }

    @Override
    public void add(CustomerSearch customerSearch) {
        customerSearchRepository.save(customerSearch);
    }

    @Override
    public List<CustomerSearch> findAll() {
        return StreamSupport.stream(customerSearchRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        customerSearchRepository.deleteById(id);
    }

    @Override
    public void addAddress(Address address) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId().toString());
        if(customerOpt.isPresent()){
            CustomerSearch customer = customerOpt.get();
            customer.getAddresses().add(address);
            customerSearchRepository.save(customer);
    }}

    @Override
    public void updateAddress(Address address) {
        // Find the customer by ID
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId().toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier
            Address existingAddress = customer.getAddresses().stream()
                    .filter(addr -> addr.getId() == address.getId()) // Assuming "id" is the unique identifier for an address
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Address not found"));

            // Update the address fields
            existingAddress.setStreet(address.getStreet());
            existingAddress.setHouseNumber(address.getHouseNumber());
            existingAddress.setDescription(address.getDescription());
            existingAddress.setDefault(address.isDefault());
            existingAddress.setDistrictId(address.getDistrictId());

            // Save the updated customer
            customerSearchRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }

    }

    @Override
    public void deleteAddress(int id, UUID customerId) {
        // Find the customer by their ID
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean addressRemoved = customer.getAddresses().removeIf(addr -> addr.getId() == id);

            if (addressRemoved) {
                // Save the updated customer after address removal
                customerSearchRepository.save(customer);
            } else {
                throw new RuntimeException("Address not found for deletion");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }


   /* @Override
    public CustomerSearch getCustomerSearchById(UUID id) {
        return customerSearchRepository.findById(id.toString()).orElseThrow(()->new RuntimeException("Customer id bulunamadı"));
    }*/
}