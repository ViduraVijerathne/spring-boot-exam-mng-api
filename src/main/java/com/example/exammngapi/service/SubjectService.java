package com.example.exammngapi.service;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.dto.SubjectDTO;
import com.example.exammngapi.entity.SubjectEntity;
import com.example.exammngapi.repo.SubjectRepo;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;
import com.example.exammngapi.validations.ROLES;
import com.example.exammngapi.validations.SubjectValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepo subjectRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthService authService;

    public List<SubjectDTO> getAllSubjects() {
        List<SubjectDTO> subjects = new ArrayList<>();
       List<SubjectEntity> subjectEntities =  subjectRepo.findAll();

       for(SubjectEntity subjectEntity : subjectEntities){
           SubjectDTO subjectDTO = modelMapper.map(subjectEntity, SubjectDTO.class);
           subjects.add(subjectDTO);
       }
        return  subjects ;
    }

    public Optional<SubjectEntity> getSubjectBySubjectId(int subjectId) {
        return subjectRepo.findById(subjectId);
    }

    public MethodResponse addSubject(AuthDTO authDTO, SubjectDTO subjectDTO) {
        List<ROLES> accessRoles = List.of(ROLES.ADMIN);

        MethodResponse methodResponse = new MethodResponse();
        if(authService.isAuthorizedUser(authDTO,accessRoles)){
            MethodResponse subjectDTOValidationResponse = SubjectValidation.validate(subjectDTO);
            if(subjectDTOValidationResponse.getCode() == ResponseCodes.RES_SUCCESS){

                SubjectEntity subjectEntity = modelMapper.map(subjectDTO,SubjectEntity.class);
                subjectEntity.setSubjectId(0);

                Optional<SubjectEntity> subjectNameCheckOptional = subjectRepo.findBySubjectName(subjectEntity.getSubjectName());
                if(subjectNameCheckOptional.isPresent()){
                    methodResponse.setCode(ResponseCodes.RES_SUBJECT_ALREADY_EXIST);
                    methodResponse.setMessage(ResponseMessages.RES_SUBJECT_ALREADY_EXIST);
                    return  methodResponse;
                }else {
                    subjectRepo.save(subjectEntity);

                    methodResponse.setCode(ResponseCodes.RES_SUCCESS);
                    methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
                    return  methodResponse;
                }


            }else{
                return subjectDTOValidationResponse;
            }

        }else{
            methodResponse.setCode(ResponseCodes.RES_UNAUTHORIZED);
            methodResponse.setMessage(ResponseMessages.RES_UNAUTHORIZED);
            return  methodResponse;
        }

    }
}
