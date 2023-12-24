package com.example.exammngapi.service;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.entity.AuthEntity;
import com.example.exammngapi.repo.AuthRepo;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    AuthRepo authRepo;

    public MethodResponse makeUser(AuthDTO authDTO) {
        MethodResponse res = new MethodResponse();

        Optional<AuthEntity> exitAuth = authRepo.findByEmail(authDTO.getEmail());

        if (exitAuth.isPresent()) {
            res.setCode(ResponseCodes.RES_USER_ALREADY_EXIST);
            res.setMessage(ResponseMessages.RES_USER_ALREADY_EXIST);
        }else{
            AuthEntity authEntity = new AuthEntity();
            authEntity.setEmail(authDTO.getEmail());
            authEntity.setPassword(authDTO.getPassword());
            authRepo.save(authEntity);
            res.setCode(ResponseCodes.RES_SUCCESS);
            res.setMessage(ResponseMessages.RES_SUCCESS);
        }

        return res;
    }
}
