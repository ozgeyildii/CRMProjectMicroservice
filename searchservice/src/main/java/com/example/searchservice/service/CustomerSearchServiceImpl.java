package com.example.searchservice.service;

import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerSearchServiceImpl implements CustomerSearchService {

    private final CustomerSearchRepository customerSearchRepository;

    public CustomerSearchServiceImpl(CustomerSearchRepository customerSearchRepository) {
        this.customerSearchRepository = customerSearchRepository;
    }

    @Override
    public void add(CustomerSearch customerSearch) {
        customerSearchRepository.save(customerSearch);
    }

    @Override
    public void delete(String id) {
        customerSearchRepository.deleteById(id);
    }

    @Override
    public List<CustomerSearch> findAll() {
        return StreamSupport.stream(customerSearchRepository.findAll().spliterator(), false)
                .map(customer -> {
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerSearch getById(String id) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(id);
        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            System.out.println("✅ [Elastic Result] " + customer);
            System.out.println("➡️ [Elastic Nested Sizes] addresses=" +
                    (customer.getAddresses() == null ? "null" : customer.getAddresses().size()) +
                    ", contactMediums=" +
                    (customer.getContactMediums() == null ? "null" : customer.getContactMediums().size()));

            if (customer.getAddresses() != null && !customer.getAddresses().isEmpty()) {
                List<Address> addresseses = customer.getAddresses();
                customer.setAddresses(addresseses);
            }

            if (customer.getContactMediums() != null && !customer.getContactMediums().isEmpty()) {
                List<ContactMedium> contactMediums = customer.getContactMediums();
                customer.setContactMediums(contactMediums);
            }

            return customer;
        }
        return null;
    }


    @Override
    public void updateCustomer(CustomerSearch customerSearch) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerSearch.getId().toString());
        if (customerOpt.isPresent()) {
            CustomerSearch existingCustomer = customerOpt.get();
            existingCustomer.setFirstName(customerSearch.getFirstName());
            existingCustomer.setLastName(customerSearch.getLastName());
            existingCustomer.setNationalId(customerSearch.getNationalId());
            existingCustomer.setDateOfBirth(customerSearch.getDateOfBirth());
            existingCustomer.setMotherName(customerSearch.getMotherName());
            existingCustomer.setFatherName(customerSearch.getFatherName());
            existingCustomer.setGender(customerSearch.getGender());
            customerSearchRepository.save(existingCustomer);
        }
    }

    @Override
    public void deleteCustomer(UUID id) {
        Optional<CustomerSearch> customerSearch = customerSearchRepository.findById(id.toString());
        customerSearch.ifPresent(customerSearchRepository::delete);
    }

    @Override
    public void addAddress(Address address) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId().toString());
        customerOpt.ifPresent(customer -> {
            if (customer.getAddresses() == null) {
                customer.setAddresses(new ArrayList<>());
            }
            customer.getAddresses().add(address);
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public void updateAddress(Address address) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId().toString());
        customerOpt.ifPresent(customer -> {
            customer.getAddresses().stream()
                    .filter(addr -> addr.getId() == address.getId())
                    .findFirst()
                    .ifPresent(existingAddress -> {
                        existingAddress.setStreet(address.getStreet());
                        existingAddress.setHouseNumber(address.getHouseNumber());
                        existingAddress.setDescription(address.getDescription());
                        existingAddress.setDefaultValue(address.isDefaultValue());
                        existingAddress.setDistrictId(address.getDistrictId());
                    });
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public void deleteAddress(int id, UUID customerId) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());
        customerOpt.ifPresent(customer -> {
            customer.getAddresses().removeIf(addr -> addr.getId() == id);
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public void softDeleteAddress(int id, UUID customerId, String deletedDate) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());
        customerOpt.ifPresent(customer -> {
            customer.getAddresses().stream()
                    .filter(addr -> id == (addr.getId()))
                    .findFirst()
                    .ifPresent(addr -> addr.setDeletedDate(LocalDateTime.now().toString()));
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public void addContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());
        customerOpt.ifPresent(customer -> {
            if (customer.getContactMediums() == null){
                customer.setContactMediums(new ArrayList<>());}
            customer.getContactMediums().add(contactMedium);
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public void updateContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());
        customerOpt.ifPresent(customer -> {
            customer.getContactMediums().stream()
                    .filter(addr -> addr.getId() == contactMedium.getId())
                    .findFirst()
                    .ifPresent(existingContactMedium -> {
                        existingContactMedium.setType(contactMedium.getType());
                        existingContactMedium.setValue(contactMedium.getValue());
                        existingContactMedium.setPrimaryValue(contactMedium.isPrimaryValue());
                    });
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public List<CustomerSearch> searchDynamic(String id, String customerNumber, String nationalId, String firstName, String lastName, String value, int page, int size) {
        return customerSearchRepository.searchDynamic(id, customerNumber, nationalId, firstName, lastName, value, page, size);
    }

    @Override
    public void deleteContactMedium(int id, UUID customerId) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());
        customerOpt.ifPresent(customer -> {
            customer.getContactMediums().removeIf(addr -> addr.getId() == id);
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public void softDeleteContactMedium(int id, UUID customerId, String deletedDate) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());
        customerOpt.ifPresent(customer -> {
            customer.getContactMediums().stream()
                    .filter(contact -> id == (contact.getId()))
                    .findFirst()
                    .ifPresent(contact -> contact.setDeletedDate(LocalDateTime.now().toString()));
            customerSearchRepository.save(customer);
        });
    }

    @Override
    public List<CustomerSearch> searchAllFields(String keyword) {
        return StreamSupport.stream(customerSearchRepository.searchAllFields(keyword).spliterator(), false)
                .map(customer -> {
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
                    List<Address> activeAddresses = customer.getAddresses().stream()
                            .filter(address -> address.getDeletedDate() == null)
                            .collect(Collectors.toList());
                    customer.setAddresses(activeAddresses);
                    return customer;
                })
                .collect(Collectors.toList());
    }


}
