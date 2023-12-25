package com.example.exammngapi.controllers;

import com.example.exammngapi.dto.SubjectDTO;
import com.example.exammngapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {


    @Autowired
    SubjectService subjectService;

    @GetMapping(path = "/")
    public ResponseEntity<List<SubjectDTO>> getSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();
        return  ResponseEntity.ok().body(subjects);
    }
}
