package com.example.exammngapi.controllers;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.dto.TeacherDTO;
import com.example.exammngapi.dto.TeacherRegistrationDTO;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.service.AuthService;
import com.example.exammngapi.service.TeacherService;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.validations.ROLES;
import com.example.exammngapi.validations.TeacherValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<?> signup(@RequestBody TeacherRegistrationDTO teacherRegistrationDTO) {
        MethodResponse teacherRegistrationValidationResponse = TeacherValidations.teacherRegistrationValidation(teacherRegistrationDTO);
        if (teacherRegistrationValidationResponse.getCode() == ResponseCodes.RES_SUCCESS) {
            AuthDTO authDTO = new AuthDTO();
            authDTO.setEmail(teacherRegistrationDTO.getEmail());
            authDTO.setPassword(teacherRegistrationDTO.getPassword());
            MethodResponse makeUserMethodResponse = authService.makeUser(authDTO, ROLES.TEACHER);
            if(makeUserMethodResponse.getCode() == ResponseCodes.RES_SUCCESS){
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setEmail(teacherRegistrationDTO.getEmail());
                teacherDTO.setFirstName(teacherRegistrationDTO.getFirstName());
                teacherDTO.setLastName(teacherRegistrationDTO.getLastName());
                teacherDTO.setProfileURL(teacherRegistrationDTO.getProfileURL());
                teacherDTO.setSubject(teacherRegistrationDTO.getSubject());

                MethodResponse teacherResponse = teacherService.makeUser(teacherDTO);
                if(teacherResponse.getCode() == ResponseCodes.RES_SUCCESS){
                    return ResponseEntity.ok().body(teacherResponse);
                }else{
                    return ResponseEntity.badRequest().body(teacherResponse);
                }
            }else{
                return ResponseEntity.badRequest().body(makeUserMethodResponse);
            }
        } else {
            return ResponseEntity.badRequest().body(teacherRegistrationValidationResponse);
        }
    }
}
