package com.example.exammngapi.service;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.entity.AdminEntity;
import com.example.exammngapi.entity.AuthEntity;
import com.example.exammngapi.repo.AdminRepo;
import com.example.exammngapi.repo.AuthRepo;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;
import com.example.exammngapi.validations.ROLES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    AuthRepo authRepo;

    public MethodResponse login(AuthDTO authDTO) {
        MethodResponse methodResponse = new MethodResponse();
        Optional<AdminEntity> adminEntityOptional = adminRepo.findByEmail(authDTO.getEmail());
        if (adminEntityOptional.isPresent()) {
            Optional<AuthEntity> authEntityOptional = authRepo.findByEmail(authDTO.getEmail());
            if (authEntityOptional.isPresent()) {
                AuthEntity authEntity = authEntityOptional.get();
                if (authEntity.getPassword().equals(authDTO.getPassword())) {
                    if (authEntity.getRole() == ROLES.ADMIN) {
                        methodResponse.setCode(ResponseCodes.RES_SUCCESS);
                        methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
                    } else {
                        methodResponse.setCode(ResponseCodes.RES_NO_ACCESS_TO_REQUEST_PAVILAGE);
                        methodResponse.setMessage(ResponseMessages.RES_NO_ACCESS_TO_REQUEST_PAVILAGE);
                    }

                } else {
                    methodResponse.setCode(ResponseCodes.RES_INVALID_PASSWORD);
                    methodResponse.setMessage(ResponseMessages.RES_INVALID_PASSWORD);
                }

            } else {
                methodResponse.setCode(ResponseCodes.RES_USER_NOT_FOUND);
                methodResponse.setMessage(ResponseMessages.RES_USER_NOT_FOUND+": user entry found but auth entry not found!  unauthorized access");
            }

        } else {
            methodResponse.setCode(ResponseCodes.RES_USER_NOT_FOUND);
            methodResponse.setMessage(ResponseMessages.RES_USER_NOT_FOUND+" : User Entry Not Found");
        }
        return methodResponse;
    }
}
