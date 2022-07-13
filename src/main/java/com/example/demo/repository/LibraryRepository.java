package com.example.demo.repository;

import com.example.demo.model.tables.pojos.Libraryuser;
import com.example.demo.model.tables.records.LibraryuserRecord;
import com.example.demo.validator.CreateLibraryInfo;
import com.example.demo.validator.UpdateLibraryInfo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.demo.model.tables.Libraryuser.LIBRARYUSER;

@Repository
@RequiredArgsConstructor
public class LibraryRepository {
    private final DSLContext db;


    public Libraryuser createLibrary(CreateLibraryInfo libraryInfo) {
        var record = db.newRecord(LIBRARYUSER, new LibraryuserRecord(null, libraryInfo.getName(),
                libraryInfo.getAuthor(), libraryInfo.getPublishedBy(), libraryInfo.getReleasedAt(),
                libraryInfo.getPrice(), libraryInfo.getCreatedAt()));
        record.store();
        return record.into(Libraryuser.class);
    }

    public Libraryuser updateLibrary(UpdateLibraryInfo libraryInfo) {
        return Objects.requireNonNull(db.update(LIBRARYUSER)
                .set(LIBRARYUSER.NAME, libraryInfo.getName())
                .set(LIBRARYUSER.AUTHOR, libraryInfo.getAuthor())
                .set(LIBRARYUSER.PUBLISHED_BY, libraryInfo.getPublishedBy())
                .set(LIBRARYUSER.RELEASED_AT, libraryInfo.getReleasedAt())
                .set(LIBRARYUSER.PRICE, libraryInfo.getPrice())
                .set(LIBRARYUSER.CREATED_AT, libraryInfo.getCreatedAt())
                .where(LIBRARYUSER.ID.eq(libraryInfo.getId()))
                .returning()
                .fetchOne())
                .map(l -> l.into(Libraryuser.class));
    }

    public Optional<Libraryuser> getLibraryById(Integer libraryId) {
        return db.selectFrom(LIBRARYUSER)
                .where(LIBRARYUSER.ID.eq(libraryId))
                .fetchOptionalInto(Libraryuser.class);
    }

    public List<Libraryuser> listLibraries() {
        return db.selectFrom(LIBRARYUSER)
                .fetchInto(Libraryuser.class);
    }

    public Optional<Libraryuser> deleteLibrary(Integer libraryId) {
        var isValidLibraryUser = getLibraryById(libraryId);
        if (isValidLibraryUser.isPresent()) {
            db.deleteFrom(LIBRARYUSER)
                    .where(LIBRARYUSER.ID.eq(libraryId))
                    .execute();
            return isValidLibraryUser;
        } else
            return Optional.empty();
    }
}
