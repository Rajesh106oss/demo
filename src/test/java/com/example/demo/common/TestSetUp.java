package com.example.demo.Test.common;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class TestSetUp {
    /*private final DSLContext db;
    private Integer libraryId;

    public void setUp() {
        var libraryRecord = db.newRecord(LIBRARY_USER, new LibraryUserRecord(null, "Coimbatore Library",
                OffsetDateTime.now()));
        libraryRecord.store();
        libraryId = libraryRecord.getId();
    }*/
}
