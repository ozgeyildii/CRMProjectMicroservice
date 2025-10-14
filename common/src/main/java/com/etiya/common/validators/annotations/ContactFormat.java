package com.etiya.common.validators.annotations;

import com.etiya.common.validators.concretes.ContactFormatImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactFormatImpl.class)
@Target(ElementType.TYPE) // <-- Fark 1: Hedef artık SINIF
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactFormat {

    // Fark 2: Özel parametreler artık bunlar
    String typeField();   // type alanının adını buraya yazacağız: "type"
    String valueField();  // value alanının adını buraya yazacağız: "value"

    // Standart kısımlar aynı kalabilir
    String message() default "İletişim tipine göre format hatalıdır.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}