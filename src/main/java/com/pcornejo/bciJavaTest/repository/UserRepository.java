package com.pcornejo.bciJavaTest.repository;

import com.pcornejo.bciJavaTest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
}
