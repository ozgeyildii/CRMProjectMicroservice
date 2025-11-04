package com.etiya.customerservice.service.abstracts;


import com.etiya.common.responses.BillingAccountResponse;
import com.etiya.customerservice.service.requests.billingaccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingaccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.billingAccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.UpdatedBillingAccountResponse;

import java.util.List;
import java.util.UUID;

public interface BillingAccountService {
    public CreatedBillingAccountResponse add(CreateBillingAccountRequest request);
    public UpdatedBillingAccountResponse update(UpdateBillingAccountRequest request);
    List<GetListBillingAccountResponse> getList();
    List<GetListBillingAccountResponse> getListByCustomerId  (UUID customerId);
    void delete(int id);

    void softDelete(int id);

    BillingAccountResponse getById(int id);


}
