package com.example.demo.validator;

import com.example.demo.model.tables.LibraryUser;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UpdateLibraryValidator implements Validator {

    private MessageSource messageSource;


    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return LibraryUser.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        UpdateLibraryInfo libraries = (UpdateLibraryInfo) target;
        var locale = LocaleContextHolder.getLocale();
        final var FieldId = "id";
        final var FieldName = "name";
        ValidationUtils.rejectIfEmpty(errors,FieldId,"Library Id is empty",
                messageSource.getMessage("library.id.empty", null, locale));
        if (errors.hasErrors())return;
            ValidationUtils.rejectIfEmpty(errors, FieldName, "Library.name.notBlank");
        if (!(libraries.getName().length() > 4 && libraries.getName().length() < 30))
            errors.rejectValue(FieldName, "Invalid Library Name");
    }
}
