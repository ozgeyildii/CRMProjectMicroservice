package com.etiya.customerservice.controller;

import com.etiya.common.responses.BillingAccountResponse;
import com.etiya.common.responses.CustomerResponse;
import com.etiya.customerservice.service.abstracts.BillingAccountService;
import com.etiya.customerservice.service.requests.billingaccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingaccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.billingAccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.UpdatedBillingAccountResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/billingAccounts")
public class BillingAccountController {

    private final BillingAccountService billingAccountService;

    public BillingAccountController(BillingAccountService billingAccountService) {
        this.billingAccountService = billingAccountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBillingAccountResponse add(@Valid @RequestBody CreateBillingAccountRequest createBillingAccountRequest) {
        return billingAccountService.add(createBillingAccountRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedBillingAccountResponse update(@RequestBody UpdateBillingAccountRequest updateBillingAccountRequest) {
        return billingAccountService.update(updateBillingAccountRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListBillingAccountResponse> get() {
        return billingAccountService.getList();
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id){
        billingAccountService.softDelete(id);
    }

    @DeleteMapping("{id}")//pathvariable ile anlaşsın diye, mapping yapsın diye
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        billingAccountService.delete(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BillingAccountResponse getById(@PathVariable int id){
        return billingAccountService.getById(id);
    }

    @GetMapping("getList/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListBillingAccountResponse> getListByCustomerId(@PathVariable UUID customerId) {
        return billingAccountService.getListByCustomerId(customerId);
    }
}
