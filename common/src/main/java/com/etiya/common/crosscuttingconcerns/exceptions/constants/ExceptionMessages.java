package com.etiya.common.crosscuttingconcerns.exceptions.constants;

public class ExceptionMessages {
    //Business Rule Messages
    public static final String BUSINESS_RULE_VIOLATION="Business Rule Violation";

    //Problem Detail Types
    public static final String TYPE_BUSINESS = "https://example.com/probs/business";
    public static final String TYPE_VALIDATION = "https://example.com/probs/validation";
    public static final String TYPE_INTERNAL = "https://example.com/probs/internal";
    public static final String TYPE_EXCEPTION = "https://example.com/probs/exception";

    //Internal server messages

    public static final String INTERNAL_ERROR = "Internal Server Error";

    //Validation Messages
    public static final String VALIDATION_ERROR = "Validation Rule Violation";
    public static final String VALIDATION_ERRORS = "Validation Errors";

    public static final String AUTHENTICATION_ERROR="Authentication Error";
    public static final String TYPE_AUTHENTICATION = "https://example.com/probs/authentication";

    public static final String OUTBOX_SERIALIZATION_ERROR = "Outbox Event Serialization Error";
    public static final String EVENT_TYPE_FIELD = "eventType";
}