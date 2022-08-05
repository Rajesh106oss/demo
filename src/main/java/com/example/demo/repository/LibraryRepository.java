package com.example.demo.repository;

import com.example.demo.model.tables.LibraryBooks;
import com.example.demo.model.tables.LibraryUser;
import com.example.demo.model.tables.records.LibraryBooksRecord;
import com.example.demo.model.tables.records.LibraryUserRecord;
import com.example.demo.validator.CreateLibraryInfo;
import com.example.demo.validator.LibraryCreationInfo;
import com.example.demo.validator.UpdateLibraryInfo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.example.demo.model.Tables.LIBRARY_BOOKS;
import static com.example.demo.model.Tables.LIBRARY_USER;

@Repository
@RequiredArgsConstructor
public class LibraryRepository {
    private final DSLContext db;


    public LibraryUser createLibrary(CreateLibraryInfo libraryInfo){
        var record= db.newRecord(LIBRARY_USER, new LibraryUserRecord
                (libraryInfo.getName(),OffsetDateTime.now()));
        record.store();
        return record.into(LibraryUser.class);
    }

    public LibraryUser updateLibrary(UpdateLibraryInfo libraryInfo) {
        return db.update(LIBRARY_USER)
                .set(LIBRARY_USER.NAME, libraryInfo.getName())
                .set(LIBRARY_USER.CREATED_AT, libraryInfo.getCreatedAt())
                .where(LIBRARY_USER.ID.eq(libraryInfo.getId()))
                .returning()
                .fetchOne()
                .map(l -> l.into(LibraryUser.class));
    }

    public Optional<LibraryUser> getLibraryById(Integer libraryId) {
        return db.selectFrom(LIBRARY_USER)
                .where(LIBRARY_USER.ID.eq(libraryId))
                .fetchOptionalInto(LibraryUser.class);
    }

    public Optional<LibraryUser> deleteLibrary(Integer libraryId) {
        var isValidLibraryUser = getLibraryById(libraryId);
        if (isValidLibraryUser.isPresent()) {
            db.deleteFrom(LIBRARY_USER)
                    .where(LIBRARY_USER.ID.eq(libraryId))
                    .execute();
            return isValidLibraryUser;
        } else
            return Optional.empty();
    }


    public LibraryBooks createLibraryBooks(LibraryCreationInfo libraryInfo) {
        var record = db.newRecord(LIBRARY_BOOKS, new LibraryBooksRecord(null,
                libraryInfo.getName(), libraryInfo.getAuthorName(),
                OffsetDateTime.now()));
        record.store();
        return record.into(LibraryBooks.class);
    }

    public Optional<LibraryBooks> getBooksById(Integer booksId) {
        return db.selectFrom(LIBRARY_BOOKS)
                .where(LIBRARY_BOOKS.ID.eq(booksId))
                .fetchOptionalInto(LibraryBooks.class);
    }


    public Optional<LibraryBooks> deleteBooksById(Integer booksId) {
        db.deleteFrom(LIBRARY_BOOKS)
                .where(LIBRARY_BOOKS.ID.eq(booksId))
                .execute();
        return Optional.empty();
    }
}

