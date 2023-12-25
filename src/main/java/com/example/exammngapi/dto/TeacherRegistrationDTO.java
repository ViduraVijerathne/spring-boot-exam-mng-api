package com.example.exammngapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherRegistrationDTO {

    int id;
    String firstName;
    String lastName;
    String email;
    String profileURL;
    SubjectDTO subject;
    String password;
    String confirmPassword;

}
