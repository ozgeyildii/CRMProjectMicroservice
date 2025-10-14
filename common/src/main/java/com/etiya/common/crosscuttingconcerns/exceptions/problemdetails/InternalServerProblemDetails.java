package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class InternalServerProblemDetails extends ProblemDetails{
    public InternalServerProblemDetails() {
        setTitle(ExceptionMessages.INTERNAL_ERROR);
        setType(ExceptionMessages.TYPE_INTERNAL);
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
