package com.example.demo.controller;

import com.example.demo.repository.LibraryRepository;
import com.example.demo.validator.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryRepository libraryRepository;
    private final CreateLibraryValidator CreateLibraryValidator;
    private final CreateBookValidator createBookValidator;
    private final UpdateLibraryValidator updateLibraryValidator;

    @PostMapping("/libraries")
    public ResponseEntity<?> createLibrary(@RequestBody CreateLibraryInfo libraryInfo) {
        final var binder = new DataBinder(libraryInfo);
        binder.setValidator(CreateLibraryValidator);
        binder.validate();
        if (binder.getBindingResult().hasErrors())
            return new ResponseEntity<>(binder.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
        libraryRepository.createLibrary(libraryInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/libraries/{libraryId}")
    public ResponseEntity<?> updateLibrary(@RequestBody UpdateLibraryInfo libraryInfo,
                                           @PathVariable Integer libraryId) {
        libraryInfo.setId(libraryId);
        var binder = new DataBinder(libraryInfo);
        binder.setValidator(updateLibraryValidator);
        binder.validate();
        if (binder.getBindingResult().hasErrors())
            return new ResponseEntity<>(binder.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
        var library = libraryRepository.updateLibrary(libraryInfo);
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @GetMapping("/libraries/{libraryId}")
    public ResponseEntity<?> findLibrary(@PathVariable Integer libraryId) {
        return libraryRepository.getLibraryById(libraryId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/libraries")
    public ResponseEntity<?> ListLibraries() {
        return new ResponseEntity<>(libraryRepository.listLibraries(), HttpStatus.OK);
    }

    @DeleteMapping("/libraries/{libraryId}")
    public ResponseEntity<?> deleteLibrary(@PathVariable Integer libraryId) {
        return libraryRepository.deleteLibrary(libraryId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/libraries/books")
    public ResponseEntity<?> createLibraryBooks(@RequestBody CreateBookInfo libraryInfo) {
        final var binder = new DataBinder(libraryInfo);
        binder.setValidator(createBookValidator);
        binder.validate();
        if (binder.getBindingResult().hasErrors())
            return new ResponseEntity<>(binder.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
        var library = libraryRepository.createLibraryBooks(libraryInfo);
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @GetMapping("libraries/{booksId}")
    public ResponseEntity<?> getLibraryInfo(@PathVariable Integer booksId) {
        return libraryRepository.getBooksById(booksId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/libraries/{booksId}")
    public ResponseEntity<?> deleteBooksId(@PathVariable Integer booksId) {
        return libraryRepository.deleteBooksById(booksId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}



