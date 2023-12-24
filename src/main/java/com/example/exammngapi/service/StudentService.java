package com.example.exammngapi.service;

import com.example.exammngapi.dto.StudentDTO;
import com.example.exammngapi.entity.StudentEntity;
import com.example.exammngapi.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public StudentDTO makeStudent(StudentDTO studentDTO){
       StudentEntity studentEntity =  modelMapper.map(studentDTO, StudentEntity.class);
       StudentEntity responseEntity =  studentRepo.save(studentEntity);
        return modelMapper.map(responseEntity, StudentDTO.class);
    }
}
