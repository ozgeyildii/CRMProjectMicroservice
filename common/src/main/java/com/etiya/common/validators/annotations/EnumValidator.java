package com.etiya.common.validators.annotations;

import com.etiya.common.validators.concretes.EnumValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class) // Bu anotasyonun mantığı EnumValidatorImpl sınıfındadır
@Target({ElementType.METHOD, ElementType.FIELD})   // Bu anotasyonu metot ve alanlarda kullanabiliriz
@Retention(RetentionPolicy.RUNTIME)              // Bu anotasyon program çalışırken de aktif olsun

// 3. İşte bizim anotasyonumuzun tanımı
public @interface EnumValidator {

    // 4. Anotasyonu kullanırken doldurmamız gereken ZORUNLU bir alan ekliyoruz.
    // Bu alan, hangi enum'a göre kontrol yapacağımızı belirtecek.
    Class<? extends Enum<?>> enumClass();

    // 5. Validasyon başarısız olursa gösterilecek VARSAYILAN hata mesajı.
    String message() default "Değer, izin verilenler listesinde değil.";

    // 6. Bu iki satır standarttır, her validasyon anotasyonunda bulunması gerekir.
    // Şimdilik içleri boş kalabilir.
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}