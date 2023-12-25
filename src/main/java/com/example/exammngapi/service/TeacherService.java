package com.example.exammngapi.service;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.dto.TeacherDTO;
import com.example.exammngapi.dto.TeacherRegistrationDTO;
import com.example.exammngapi.entity.SubjectEntity;
import com.example.exammngapi.entity.TeacherEntity;
import com.example.exammngapi.repo.TeacherRepo;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ModelMapper modelMapper;

    public MethodResponse makeUser(TeacherDTO teacherDTO) {
        MethodResponse methodResponse = new MethodResponse();
        Optional<SubjectEntity> subjectEntityOptional = subjectService.getSubjectBySubjectId(teacherDTO.getSubject().getSubjectId());

        if (subjectEntityOptional.isPresent()) {
           TeacherEntity teacherEntity =  modelMapper.map(teacherDTO, TeacherEntity.class);
              teacherEntity.setSubject(subjectEntityOptional.get());
                TeacherEntity responseEntity =  teacherRepo.save(teacherEntity);
                methodResponse.setCode(ResponseCodes.RES_SUCCESS);
                methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
                methodResponse.setData(modelMapper.map(responseEntity, TeacherDTO.class));
        } else {
            methodResponse.setCode(ResponseCodes.RES_SUBJECT_NOT_FOUND);
            methodResponse.setMessage(ResponseMessages.RES_SUBJECT_NOT_FOUND);
        }

        return methodResponse;


    }

    public MethodResponse getTeacherById(int id) {
        MethodResponse methodResponse = new MethodResponse();
        Optional<TeacherEntity> teacherEntityOptional = teacherRepo.findById(id);
        if (teacherEntityOptional.isPresent()) {
            methodResponse.setCode(ResponseCodes.RES_SUCCESS);
            methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
            methodResponse.setData(modelMapper.map(teacherEntityOptional.get(), TeacherDTO.class));
        } else {
            methodResponse.setCode(ResponseCodes.RES_TEACHER_NOT_FOUND);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_NOT_FOUND);
        }
        return methodResponse;
    }
}
