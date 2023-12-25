package com.example.exammngapi.controllers;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.dto.SubjectDTO;
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

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AuthService authService;

    @Autowired
    SubjectService subjectService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
       MethodResponse methodResponse =  adminService.login(authDTO);
       if(methodResponse.getCode() == ResponseCodes.RES_SUCCESS){
              return ResponseEntity.ok(methodResponse);
       }else{
              return ResponseEntity.badRequest().body(methodResponse);
       }
    }

    @PostMapping("/subject")
    public  ResponseEntity<?> addSubject(@RequestHeader(HttpHeaders.AUTHORIZATION) String token ,@RequestBody SubjectDTO subjectDTO){

        List<ROLES> accessRoles = List.of(ROLES.ADMIN);


        AuthDTO authDTO = AuthTokenConverter.convertToDto(token);

        if(authService.isAuthorizedUser(authDTO,accessRoles)){
           MethodResponse methodResponse  =  subjectService.addSubject(authDTO,subjectDTO);
           if(methodResponse.getCode() == ResponseCodes.RES_SUCCESS){
               return ResponseEntity.ok(methodResponse);
           }else{
                return ResponseEntity.badRequest().body(methodResponse);
           }
        }else{
            MethodResponse methodResponse = new MethodResponse();
            methodResponse.setCode(ResponseCodes.RES_UNAUTHORIZED);
            methodResponse.setMessage(ResponseMessages.RES_UNAUTHORIZED);
            return ResponseEntity.badRequest().body(methodResponse);
        }

    }
}
