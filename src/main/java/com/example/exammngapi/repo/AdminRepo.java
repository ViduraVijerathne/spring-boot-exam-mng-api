package com.example.exammngapi.repo;

import com.example.exammngapi.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity,Integer> {
    Optional<AdminEntity> findByEmail(String email);
}
