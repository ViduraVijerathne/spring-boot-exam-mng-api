package com.example.exammngapi.service;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.entity.AuthEntity;
import com.example.exammngapi.entity.StudentEntity;
import com.example.exammngapi.repo.AuthRepo;
import com.example.exammngapi.repo.StudentRepo;
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

    @Autowired
    StudentRepo studentRepo;

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

    public MethodResponse login(AuthDTO authDTO) {
        MethodResponse res = new MethodResponse();

        Optional<AuthEntity> exitAuth = authRepo.findByEmail(authDTO.getEmail());

        if (exitAuth.isPresent()) {
            AuthEntity authEntity = exitAuth.get();
            if (authEntity.getPassword().equals(authDTO.getPassword())) {
                res.setCode(ResponseCodes.RES_SUCCESS);
                res.setMessage(ResponseMessages.RES_SUCCESS);
                Optional<StudentEntity> studentEntity = studentRepo.findByEmail(authEntity.getEmail());
                studentEntity.ifPresent(res::setData);

            } else {
                res.setCode(ResponseCodes.RES_PASSWORD_NOT_MATCH);
                res.setMessage(ResponseMessages.RES_PASSWORD_NOT_MATCH);
            }
        } else {
            res.setCode(ResponseCodes.RES_USER_NOT_FOUND);
            res.setMessage(ResponseMessages.RES_USER_NOT_FOUND);
        }

        return res;
    }
}
