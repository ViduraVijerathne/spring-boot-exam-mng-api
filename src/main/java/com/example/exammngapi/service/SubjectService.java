package com.example.exammngapi.service;

import com.example.exammngapi.dto.SubjectDTO;
import com.example.exammngapi.entity.SubjectEntity;
import com.example.exammngapi.repo.SubjectRepo;
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
}
