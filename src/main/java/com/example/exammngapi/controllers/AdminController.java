package com.example.exammngapi.controllers;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.dto.SubjectDTO;
import com.example.exammngapi.entity.SubjectEntity;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.service.AdminService;
import com.example.exammngapi.service.AuthService;
import com.example.exammngapi.service.SubjectService;
import com.example.exammngapi.utils.AuthTokenConverter;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;
import com.example.exammngapi.validations.ROLES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AuthService authService;

    @Autowired
    SubjectService subjectService;

    private final  List<ROLES> accessRoles = List.of(ROLES.ADMIN);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        MethodResponse methodResponse = adminService.login(authDTO);
        if (methodResponse.getCode() == ResponseCodes.RES_SUCCESS) {
            return ResponseEntity.ok(methodResponse);
        } else {
            return ResponseEntity.badRequest().body(methodResponse);
        }
    }

    @PostMapping("/subjects")
    public ResponseEntity<?> addSubject(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody SubjectDTO subjectDTO) {




        AuthDTO authDTO = AuthTokenConverter.convertToDto(token);

        if (authService.isAuthorizedUser(authDTO, accessRoles)) {
            MethodResponse methodResponse = subjectService.addSubject(authDTO, subjectDTO);
            if (methodResponse.getCode() == ResponseCodes.RES_SUCCESS) {
                return ResponseEntity.ok(methodResponse);
            } else {
                return ResponseEntity.badRequest().body(methodResponse);
            }
        } else {
            MethodResponse methodResponse = new MethodResponse();
            methodResponse.setCode(ResponseCodes.RES_UNAUTHORIZED);
            methodResponse.setMessage(ResponseMessages.RES_UNAUTHORIZED);
            return ResponseEntity.badRequest().body(methodResponse);
        }

    }

    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubjects(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {


        AuthDTO authDTO = AuthTokenConverter.convertToDto(token);

        if (authService.isAuthorizedUser(authDTO, accessRoles)) {
            List<SubjectDTO> subjects = subjectService.getAllSubjects();
            return ResponseEntity.ok(subjects);
        } else {
            MethodResponse methodResponse = new MethodResponse();
            methodResponse.setCode(ResponseCodes.RES_UNAUTHORIZED);
            methodResponse.setMessage(ResponseMessages.RES_UNAUTHORIZED);
            return ResponseEntity.badRequest().body(methodResponse);
        }
    }

    @GetMapping("/subjects/{subjectId}")
    public ResponseEntity<?> getSubjectBySubjectId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int subjectId) {
        AuthDTO authDTO = AuthTokenConverter.convertToDto(token);

        if (authService.isAuthorizedUser(authDTO, accessRoles)) {
            Optional<SubjectEntity> subjects = subjectService.getSubjectBySubjectId(subjectId);
            return ResponseEntity.ok(subjects.get());
        } else {
            MethodResponse methodResponse = new MethodResponse();
            methodResponse.setCode(ResponseCodes.RES_UNAUTHORIZED);
            methodResponse.setMessage(ResponseMessages.RES_UNAUTHORIZED);
            return ResponseEntity.badRequest().body(methodResponse);
        }
    }


    @PutMapping("/subjects/{subjectId}")
    public ResponseEntity<?> updateSubject(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable int subjectId, @RequestBody SubjectDTO subjectDTO) {
        AuthDTO authDTO = AuthTokenConverter.convertToDto(token);

        if (authService.isAuthorizedUser(authDTO, accessRoles)) {
            MethodResponse methodResponse = subjectService.updateSubject(authDTO, subjectId, subjectDTO);
            if (methodResponse.getCode() == ResponseCodes.RES_SUCCESS) {
                return ResponseEntity.ok(methodResponse);
            } else {
                return ResponseEntity.badRequest().body(methodResponse);
            }
        } else {
            MethodResponse methodResponse = new MethodResponse();
            methodResponse.setCode(ResponseCodes.RES_UNAUTHORIZED);
            methodResponse.setMessage(ResponseMessages.RES_UNAUTHORIZED);
            return ResponseEntity.badRequest().body(methodResponse);
        }
    }

    @PutMapping("/subjects/")
    public ResponseEntity<?> updateSubject(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody SubjectDTO subjectDTO) {
        AuthDTO authDTO = AuthTokenConverter.convertToDto(token);

        if (authService.isAuthorizedUser(authDTO, accessRoles)) {
            MethodResponse methodResponse = subjectService.updateSubject(authDTO, subjectDTO);
            if (methodResponse.getCode() == ResponseCodes.RES_SUCCESS) {
                return ResponseEntity.ok(methodResponse);
            } else {
                return ResponseEntity.badRequest().body(methodResponse);
            }
        } else {
            MethodResponse methodResponse = new MethodResponse();
            methodResponse.setCode(ResponseCodes.RES_UNAUTHORIZED);
            methodResponse.setMessage(ResponseMessages.RES_UNAUTHORIZED);
            return ResponseEntity.badRequest().body(methodResponse);
        }
    }


}
