package com.example.demo.validator;

import com.example.demo.model.tables.pojos.Libraryuser;
import com.example.demo.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class CreateValidator implements Validator {
private final LibraryRepository libraryRepository;
private final DSLContext db;
private final MessageSource messageSource;


    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Libraryuser.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Libraryuser libraryuser = (Libraryuser) target;
        var locale = LocaleContextHolder.getLocale();
        final var libraryFieldName = "name";
        if (!(libraryuser.getName().length() > 5 && libraryuser.getName().length() < 100)) {
            errors.rejectValue(libraryFieldName, "INVALID",
                    messageSource.getMessage("libraries.name.size",
                            null, locale));
        }
        if (!(libraryuser.getName().length() > 5)) {
            errors.rejectValue(libraryFieldName, "INVALID",
                    messageSource.getMessage("libraries.name.size",
                            null, locale));
        }

    }
}
