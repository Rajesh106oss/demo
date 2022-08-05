package com.example.demo.validator;

import com.example.demo.model.tables.pojos.LibraryBooks;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CreateBookValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return LibraryBooks.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        LibraryCreationInfo libraries = (LibraryCreationInfo) target;
        final var FieldName = "name";
        ValidationUtils.rejectIfEmpty(errors, FieldName, "Books.name.notBlank");
        if (!(libraries.getName().length() > 2 && libraries.getName().length() < 20))
            errors.rejectValue(FieldName, "Invalid Book Name");
    }
}
