package com.example.demo.controller;

import com.example.demo.model.tables.pojos.Libraryuser;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.validator.CreateLibraryInfo;
import com.example.demo.validator.CreateValidator;
import com.example.demo.validator.UpdateLibraryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryRepository libraryRepository;
    private final CreateValidator createValidator;


    @PostMapping("/libraries")
    public ResponseEntity<?> createLibrary(@RequestBody CreateLibraryInfo libraryInfo) {
        DataBinder binder = new DataBinder(libraryInfo);
        binder.setValidator(createValidator);
        binder.validate();
        if (binder.getBindingResult().hasErrors())
            return new ResponseEntity<>(binder.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
        var library = libraryRepository.createLibrary(libraryInfo);
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @PutMapping("/libraries/{libraryId}")
    public ResponseEntity<?> updateLibrary(@RequestBody UpdateLibraryInfo libraryInfo,
                                           @PathVariable Integer libraryId) {
        libraryInfo.setId(libraryId);
        var library = libraryRepository.updateLibrary(libraryInfo);
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @GetMapping("/libraries/{libraryId}")
    public ResponseEntity<?> findLibrary(@PathVariable Integer libraryId) {
        return libraryRepository.getLibraryById(libraryId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/libraries")
    public ResponseEntity<List<Libraryuser>> listLibraries() {
        return new ResponseEntity<>(libraryRepository.listLibraries(), HttpStatus.OK);
    }

    @DeleteMapping("/libraries/{libraryId}")
    public ResponseEntity<?> deleteLibrary(@PathVariable Integer libraryId) {
        return libraryRepository.deleteLibrary(libraryId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


