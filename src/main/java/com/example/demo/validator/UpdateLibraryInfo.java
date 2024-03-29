package com.example.demo.validator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLibraryInfo {
    @JsonIgnore
    private Integer id;
    private String name;
    private OffsetDateTime createdAt;
}

