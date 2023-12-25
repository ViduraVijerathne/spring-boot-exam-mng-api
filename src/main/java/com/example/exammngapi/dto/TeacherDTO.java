package com.example.exammngapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDTO {

    int id;
    String firstName;
    String lastName;
    String email;
    String profileURL;
    SubjectDTO subject;
}
