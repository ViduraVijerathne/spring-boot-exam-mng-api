package com.example.exammngapi.repo;

import com.example.exammngapi.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity,Integer> {

    Optional<StudentEntity> findByEmail(String email);
}
