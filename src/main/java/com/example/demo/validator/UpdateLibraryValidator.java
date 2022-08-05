package com.example.demo.validator;

import com.example.demo.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UpdateLibraryValidator implements Validator {
    private final LibraryRepository libraryRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return UpdateLibraryInfo.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        UpdateLibraryInfo updateLibraryInfo = (UpdateLibraryInfo) target;
        final var FieldId = "id";
        final var FieldName = "name";
        ValidationUtils.rejectIfEmpty(errors, FieldId, "Library Id is empty");
        if (errors.hasErrors()) return;
        ValidationUtils.rejectIfEmpty(errors, FieldName, "Library name is empty");
        if (errors.hasErrors()) return;
        if (!(updateLibraryInfo.getName().length() > 4 && updateLibraryInfo.getName().length() < 30))
            errors.rejectValue(FieldName, "Invalid Library Name");
        if (errors.hasErrors()) return;
        var validLibraryId = libraryRepository.getLibraryById(updateLibraryInfo.getId());
        if (validLibraryId.isEmpty())
            errors.rejectValue("id", "Please provide valid libraryId");
    }
}
