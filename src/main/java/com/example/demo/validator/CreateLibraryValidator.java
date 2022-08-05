package com.example.demo.validator;

import com.example.demo.model.tables.LibraryUser;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CreateLibraryValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return LibraryUser.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        CreateLibraryInfo libraries = (CreateLibraryInfo) target;
        final var FieldName = "name";
        ValidationUtils.rejectIfEmpty(errors, FieldName, "Library.name.notBlank");
        if (!(libraries.getName().length() > 3 && libraries.getName().length() < 50))
            errors.rejectValue(FieldName, "Invalid Library Name");
    }
}
