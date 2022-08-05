package com.example.demo.validator;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLibraryInfo {

    private String name;
    private OffsetDateTime createdAt;
}

