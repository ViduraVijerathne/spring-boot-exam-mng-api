package com.example.exammngapi.controllers;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.service.AdminService;
import com.example.exammngapi.utils.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
       MethodResponse methodResponse =  adminService.login(authDTO);
       if(methodResponse.getCode() == ResponseCodes.RES_SUCCESS){
              return ResponseEntity.ok(methodResponse);
       }else{
              return ResponseEntity.badRequest().body(methodResponse);
       }
    }
}
