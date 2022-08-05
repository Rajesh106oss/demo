package com.example.demo.Test.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestTearDown {
   /* private final DSLContext db;

    @Autowired
    TestSetUp setUp;

    public void tearDown() {
        db.batch(db.delete(LIBRARY_BOOKS), db.delete(LIBRARY_USER)).execute();
    }*/
}
