/*
 * This file is generated by jOOQ.
 */
package com.example.demo.model.tables;


import com.example.demo.model.Keys;
import com.example.demo.model.Public;
import com.example.demo.model.tables.records.LibraryUserRecord;

import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LibraryUser extends TableImpl<LibraryUserRecord> {

    private static final long serialVersionUID = 1595906834;

    /**
     * The reference instance of <code>public.library_user</code>
     */
    public static final LibraryUser LIBRARY_USER = new LibraryUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LibraryUserRecord> getRecordType() {
        return LibraryUserRecord.class;
    }

    /**
     * The column <code>public.library_user.id</code>.
     */
    public final TableField<LibraryUserRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('library_user_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.library_user.name</code>.
     */
    public final TableField<LibraryUserRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.library_user.created_at</code>.
     */
    public final TableField<LibraryUserRecord, OffsetDateTime> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * Create a <code>public.library_user</code> table reference
     */
    public LibraryUser() {
        this(DSL.name("library_user"), null);
    }

    /**
     * Create an aliased <code>public.library_user</code> table reference
     */
    public LibraryUser(String alias) {
        this(DSL.name(alias), LIBRARY_USER);
    }

    /**
     * Create an aliased <code>public.library_user</code> table reference
     */
    public LibraryUser(Name alias) {
        this(alias, LIBRARY_USER);
    }

    private LibraryUser(Name alias, Table<LibraryUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private LibraryUser(Name alias, Table<LibraryUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> LibraryUser(Table<O> child, ForeignKey<O, LibraryUserRecord> key) {
        super(child, key, LIBRARY_USER);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<LibraryUserRecord, Integer> getIdentity() {
        return Keys.IDENTITY_LIBRARY_USER;
    }

    @Override
    public LibraryUser as(String alias) {
        return new LibraryUser(DSL.name(alias), this);
    }

    @Override
    public LibraryUser as(Name alias) {
        return new LibraryUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public LibraryUser rename(String name) {
        return new LibraryUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public LibraryUser rename(Name name) {
        return new LibraryUser(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, OffsetDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
