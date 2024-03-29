package com.example.demo.validator;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookInfo {
    private String name;
    private String authorName;
    private Integer libraryId;
    private OffsetDateTime releasedAt;



    }

