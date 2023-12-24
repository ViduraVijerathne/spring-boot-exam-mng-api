package com.example.exammngapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String profileURL;
}
