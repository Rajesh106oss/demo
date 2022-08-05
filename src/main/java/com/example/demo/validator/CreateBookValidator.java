package com.example.demo.validator;

import com.example.demo.model.tables.pojos.LibraryBooks;
import com.example.demo.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@RequiredArgsConstructor
public class CreateBookValidator implements Validator {
    private final LibraryRepository libraryRepository;
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return LibraryBooks.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        CreateBookInfo createBookInfo = (CreateBookInfo) target;
        final var fieldName = "name";
        ValidationUtils.rejectIfEmpty(errors, fieldName, "Books.name.notBlank");
        if (!(createBookInfo.getName().length() > 2 && createBookInfo.getName().length() < 20))
            errors.rejectValue(fieldName, "Invalid Book Name");
        if (errors.hasErrors()) return;
        var validLibraryId = libraryRepository.getLibraryById(createBookInfo.getLibraryId());
        if (validLibraryId.isEmpty())
            errors.rejectValue("libraryId", "Please provide valid libraryId");
    }
}
