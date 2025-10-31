package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class AuthenticationProblemDetails extends ProblemDetails{

    public AuthenticationProblemDetails() {
        setTitle(ExceptionMessages.AUTHENTICATION_ERROR);
        setType(ExceptionMessages.TYPE_AUTHENTICATION);
        setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
