package com.example.exammngapi.controllers;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.dto.StudentDTO;
import com.example.exammngapi.dto.StudentRegisterDTO;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.service.AuthService;
import com.example.exammngapi.service.StudentService;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;
import com.example.exammngapi.validations.StudentValidations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/signup")
    public ResponseEntity<?> signup(@RequestBody StudentRegisterDTO studentRegisterDTO) {
        MethodResponse studentRegistrationValidationResponse = StudentValidations.studentRegistrationValidation(studentRegisterDTO);

        if (studentRegistrationValidationResponse.getCode() == ResponseCodes.RES_SUCCESS) {
            AuthDTO authDTO = new AuthDTO();
            authDTO.setEmail(studentRegisterDTO.getEmail());
            authDTO.setPassword(studentRegisterDTO.getPassword());

            MethodResponse response = authService.makeUser(authDTO);

            if (response.getCode() == ResponseCodes.RES_SUCCESS) {
                StudentDTO studentDTO = modelMapper.map(studentRegisterDTO, StudentDTO.class);
                StudentDTO responseStudentDTO = studentService.makeStudent(studentDTO);
                MethodResponse studentResponse = new MethodResponse();
                studentResponse.setCode(ResponseCodes.RES_SUCCESS);
                studentResponse.setMessage(ResponseMessages.RES_SUCCESS);
                studentResponse.setData(responseStudentDTO);
                return ResponseEntity.ok().body(studentResponse);

            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            return ResponseEntity.badRequest().body(studentRegistrationValidationResponse);
        }


    }

}
