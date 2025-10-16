package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.IndividualCustomerMapper;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.rules.IndividualCustomerBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.customer.CreateCustomerProducer;
import org.springframework.stereotype.Service;

@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final CreateCustomerProducer createCustomerProducer;

    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerBusinessRules individualCustomerBusinessRules, CreateCustomerProducer createCustomerProducer) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.individualCustomerBusinessRules = individualCustomerBusinessRules;
        this.createCustomerProducer = createCustomerProducer;
    }

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        individualCustomerBusinessRules.checkIfIndividualCustomerExistsByIdentityNumber((request.getNationalId()));
        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(request);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);
        CreateCustomerEvent event = new CreateCustomerEvent(createdIndividualCustomer.getId().toString(),
                createdIndividualCustomer.getCustomerNumber(),
                createdIndividualCustomer.getFirstName(),
                createdIndividualCustomer.getLastName(),
                createdIndividualCustomer.getNationalId(),
                createdIndividualCustomer.getDateOfBirth().toString(),
                createdIndividualCustomer.getMotherName(),
                createdIndividualCustomer.getFatherName(),
                createdIndividualCustomer.getGender());
        createCustomerProducer.produceCustomerCreated(event);
        CreatedIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);
        return response;
    }
}
