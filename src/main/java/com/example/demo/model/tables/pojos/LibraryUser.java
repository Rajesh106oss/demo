/*
 * This file is generated by jOOQ.
 */
package com.example.demo.model.tables.pojos;


import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;


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
public class LibraryUser implements Serializable {

    private static final long serialVersionUID = 1385766895;

    private Integer        id;
    private String         name;
    private OffsetDateTime createdAt;

    public LibraryUser() {}

    public LibraryUser(LibraryUser value) {
        this.id = value.id;
        this.name = value.name;
        this.createdAt = value.createdAt;
    }

    public LibraryUser(
        Integer        id,
        String         name,
        OffsetDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LibraryUser (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }
}
