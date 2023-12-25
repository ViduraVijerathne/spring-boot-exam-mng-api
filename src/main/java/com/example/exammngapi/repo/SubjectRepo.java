package com.example.exammngapi.repo;

import com.example.exammngapi.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity,Integer>{
    Optional<SubjectEntity> findBySubjectName(String subjectName);
}
