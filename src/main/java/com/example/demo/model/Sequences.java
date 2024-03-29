/*
 * This file is generated by jOOQ.
 */
package com.example.demo.model;


import javax.annotation.processing.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.library_books_id_seq</code>
     */
    public static final Sequence<Integer> LIBRARY_BOOKS_ID_SEQ = new SequenceImpl<Integer>("library_books_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.library_books_library_id_seq</code>
     */
    public static final Sequence<Integer> LIBRARY_BOOKS_LIBRARY_ID_SEQ = new SequenceImpl<Integer>("library_books_library_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

    /**
     * The sequence <code>public.library_user_id_seq</code>
     */
    public static final Sequence<Integer> LIBRARY_USER_ID_SEQ = new SequenceImpl<Integer>("library_user_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER.nullable(false));
}
