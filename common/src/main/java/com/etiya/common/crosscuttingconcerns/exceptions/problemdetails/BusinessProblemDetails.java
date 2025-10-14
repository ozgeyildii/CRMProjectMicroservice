package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails() {
        setTitle(ExceptionMessages.BUSINESS_RULE_VIOLATION);
        setType(ExceptionMessages.TYPE_BUSINESS);
        setStatus(HttpStatus.BAD_REQUEST.value());
    }
}
