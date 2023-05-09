package com.pcornejo.bciJavaTest.repository;

import com.pcornejo.bciJavaTest.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
