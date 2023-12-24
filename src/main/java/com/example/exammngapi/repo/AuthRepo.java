package com.example.exammngapi.repo;

import com.example.exammngapi.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<AuthEntity,Integer> {
    Optional<AuthEntity> findByEmail(String email);
}
