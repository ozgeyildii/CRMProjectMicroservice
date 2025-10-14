package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessages;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ValidationProblemDetails extends ProblemDetails{
    private Map<String,String> validationErrors;

    public ValidationProblemDetails(){
        setTitle(ExceptionMessages.VALIDATION_ERROR);
        setType(ExceptionMessages.TYPE_VALIDATION);
        setStatus(HttpStatus.BAD_REQUEST.value());
    }


    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}