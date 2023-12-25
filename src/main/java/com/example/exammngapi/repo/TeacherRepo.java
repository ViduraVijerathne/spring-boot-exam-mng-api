package com.example.exammngapi.repo;

import com.example.exammngapi.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<TeacherEntity,Integer> {
}
