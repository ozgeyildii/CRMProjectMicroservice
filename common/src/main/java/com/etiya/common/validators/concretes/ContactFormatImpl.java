package com.etiya.common.validators.concretes;

import com.etiya.common.validators.annotations.ContactFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @ContactFormat anotasyonunun arkasındaki validasyon mantığını içerir.
 * Bu sınıf, bir nesnenin tamamını alarak, bir alanın değerine göre
 * başka bir alanın formatını kontrol eder.
 */
public class ContactFormatImpl implements ConstraintValidator<ContactFormat, Object> {

    // Regex'leri sabit (final) olarak tanımlıyoruz.
    private static final String EMAIL_REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String TURKISH_PHONE_REGEX =
            "^(?:\\+90|0)?\\s*?(?:\\(?([2-5][0-9]{2})\\)?\\s*?([0-9]{3})\\s*?([0-9]{2})\\s*?([0-9]{2})|([2-5][0-9]{9}))$";


    // Anotasyondan gelen alan isimlerini tutacak değişkenler
    private String typeFieldName;
    private String valueFieldName;

    /**
     * Validator'ı hazırlama metodu. Anotasyondan alan isimlerini okur ve sınıf değişkenlerine atar.
     */
    @Override
    public void initialize(ContactFormat constraintAnnotation) {
        this.typeFieldName = constraintAnnotation.typeField();
        this.valueFieldName = constraintAnnotation.valueField();
    }

    /**
     * Asıl validasyon mantığının çalıştığı metot.
     * @param dtoObject DTO nesnesinin kendisi (örneğin CreateContactMediumRequest)
     * @param context Validasyon context'i
     * @return Alanlar arasındaki kurala göre geçerli ise true, değilse false
     */
    @Override
    public boolean isValid(Object dtoObject, ConstraintValidatorContext context) {
        // Spring'in BeanWrapper'ını kullanarak DTO'nun alanlarına ismen güvenle erişeceğiz.
        BeanWrapper beanWrapper = new BeanWrapperImpl(dtoObject);

        // DTO'dan alanların değerlerini alıyoruz.
        Object typeValue = beanWrapper.getPropertyValue(typeFieldName);
        Object valueValue = beanWrapper.getPropertyValue(valueFieldName);

        // Eğer type veya value alanları boşsa, bu bizim işimiz değil.
        // O işi @NotBlank halletmeli. Sorun yokmuş gibi davranıp geçmesine izin veriyoruz.
        if (typeValue == null || valueValue == null || valueValue.toString().trim().isEmpty()) {
            return true;
        }

        // Değerleri String'e çevirelim.
        String typeString = typeValue.toString();
        String valueString = valueValue.toString();

        // ---- ASIL KONTROL MANTIĞI ----

        if (typeString.equalsIgnoreCase("EMAIL")) {
            // Eğer tip EMAIL ise, value alanı EMAIL_REGEX'e uymalı.
            return valueString.matches(EMAIL_REGEX);

        } else if (typeString.equalsIgnoreCase("PHONE")) {
            // Eğer tip PHONE ise, value alanı TURKISH_PHONE_REGEX'e uymalı.
            return valueString.matches(TURKISH_PHONE_REGEX);
        }

        // Eğer tip ne EMAIL ne de PHONE ise, bu validator'ın görevi olmadığı için true dönüyoruz.
        // Bu tipin geçerli olup olmadığını @EnumValidator zaten kontrol ediyor. Herkes kendi işini yapar.
        return true;
    }
}