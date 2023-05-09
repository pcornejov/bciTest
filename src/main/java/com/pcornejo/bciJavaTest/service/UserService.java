package com.pcornejo.bciJavaTest.service;

import com.pcornejo.bciJavaTest.dto.CreateUserDTO;
import com.pcornejo.bciJavaTest.dto.CreatedUserDTO;
import com.pcornejo.bciJavaTest.dto.UpdateUserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    CreatedUserDTO createUser(CreateUserDTO user) throws Exception;

    CreatedUserDTO getUserById(UUID userId) throws Exception;

    List<CreatedUserDTO> getAllUsers() throws Exception;

    CreatedUserDTO updateUser(UpdateUserDTO user) throws Exception;

    void deleteUser(UUID userId) throws Exception;
}
