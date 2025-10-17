package com.example.searchservice.service;

import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.repository.CustomerSearchRepository;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
       // return StreamSupport.stream(customerSearchRepository.findAll().spliterator(),false).collect(Collectors.toList());
        return StreamSupport.stream(customerSearchRepository.findAll().spliterator(), false)
                .map(customer -> {
                    // deletedDate'i null olmayan adresleri filtrele
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
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

    @Override
    public void softDeleteAddress(int id, UUID customerId, String deletedDate) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
          customer.getAddresses().stream().filter(addr -> id==(addr.getId()))
                   .findFirst()
                   .ifPresent(addr -> addr.setDeletedDate(LocalDateTime.now().toString()));

            customerSearchRepository.save(customer);

        } else {
            throw new RuntimeException("Customer not found");
        }

    }

    @Override
    public void addContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());
        if(customerOpt.isPresent()){
            CustomerSearch customer = customerOpt.get();
            customer.getContactMediums().add(contactMedium);
            customerSearchRepository.save(customer);
        }}

    @Override
    public void updateContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier
            ContactMedium existingContactMedium = customer.getContactMediums().stream()
                    .filter(addr -> addr.getId() == contactMedium.getId()) // Assuming "id" is the unique identifier for an address
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("ContactMedium not found"));

            // Update the address fields
            existingContactMedium.setType(contactMedium.getType());
            existingContactMedium.setValue(contactMedium.getValue());
            existingContactMedium.setPrimary(contactMedium.isPrimary());

            // Save the updated customer
            customerSearchRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void deleteContactMedium(int id, UUID customerId) {
        // Find the customer by their ID
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean contactMediumRemoved = customer.getContactMediums().removeIf(addr -> addr.getId() == id);


            if (contactMediumRemoved) {
                // Save the updated customer after address removal
                customerSearchRepository.save(customer);
            } else {
                throw new RuntimeException("Contact medium not found for deletion");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void softDeleteContactMedium(int id, UUID customerId, String deletedDate) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            customer.getContactMediums().stream().filter(contact -> id==(contact.getId()))
                    .findFirst()
                    .ifPresent(contact -> contact.setDeletedDate(LocalDateTime.now().toString()));

            customerSearchRepository.save(customer);

        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public List<CustomerSearch> searchAllFields(String keyword) {
        return StreamSupport.stream(customerSearchRepository.searchAllFields(keyword).spliterator(), false)
                .map(customer -> {
                    // deletedDate'i null olmayan adresleri filtrele
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerSearch> searchByMatchedName(String name) {
        return StreamSupport.stream(customerSearchRepository.findByFirstNameMatch(name).spliterator(), false)
                .map(customer -> {
                    // deletedDate'i null olmayan adresleri filtrele
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerSearch> searchByExactValue(String nationalId) {
        return StreamSupport.stream(customerSearchRepository.findByExactValue(nationalId).spliterator(), false)
                .map(customer -> {
                    // deletedDate'i null olmayan adresleri filtrele
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerSearch> searchBySimilarName(String name) {
        return StreamSupport.stream(customerSearchRepository.findBySimilarName(name).spliterator(), false)
                .map(customer -> {
                    // deletedDate'i null olmayan adresleri filtrele
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerSearch> searchByDateRange(String start, String end) {
        return StreamSupport.stream(customerSearchRepository.findByDateRange(start, end).spliterator(), false)
                .map(customer -> {
                    // deletedDate'i null olmayan adresleri filtrele
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<CustomerSearch> searchByNameAndGender(String name, String gender) {
        return StreamSupport.stream(customerSearchRepository.findFirstNameAndGender(name, gender).spliterator(), false)
                .map(customer -> {
                    // deletedDate'i null olmayan adresleri filtrele
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
    }


   /* @Override
    public CustomerSearch getCustomerSearchById(UUID id) {
        return customerSearchRepository.findById(id.toString()).orElseThrow(()->new RuntimeException("Customer id bulunamadı"));
    }*/
}