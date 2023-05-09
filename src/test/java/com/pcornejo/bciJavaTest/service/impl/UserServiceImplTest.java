package com.pcornejo.bciJavaTest.service.impl;

import com.pcornejo.bciJavaTest.dto.CreateUserDTO;
import com.pcornejo.bciJavaTest.dto.CreatedUserDTO;
import com.pcornejo.bciJavaTest.dto.PhoneDTO;
import com.pcornejo.bciJavaTest.dto.UpdateUserDTO;
import com.pcornejo.bciJavaTest.entity.Phone;
import com.pcornejo.bciJavaTest.entity.User;
import com.pcornejo.bciJavaTest.repository.UserRepository;
import com.pcornejo.bciJavaTest.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    private CreatedUserDTO created = new CreatedUserDTO();
    private CreateUserDTO create = new CreateUserDTO();
    @Mock
    private JwtUtil jwtUtil;
    @InjectMocks
    private UserServiceImpl userServicempl;

    private User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setName("test");
        user.setEmail("test@test.cl");
        user.setPassword("test");
    }

    @Test
    void createUser() {
        when(userRepository.existsByEmail("test@test.cl")).thenReturn(false);
        when(modelMapper.map(create, User.class)).thenReturn(user);
        when(jwtUtil.generateAccessToken(user)).thenReturn("test");
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, CreatedUserDTO.class)).thenReturn(created);
        CreatedUserDTO test = userServicempl.createUser(create);
        assertNotNull(user);
        assertEquals(test, created);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"))).thenReturn(Optional.of(user));
        when(modelMapper.map(user, CreatedUserDTO.class)).thenReturn(created);
        CreatedUserDTO test = userServicempl.getUserById(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"));
        assertNotNull(test);
        assertEquals(test, created);

    }

    @Test
    void getAllUsers() {
        when(modelMapper.map(any(), any())).thenReturn(created);
        this.modelMapper = new ModelMapper();
        created.setName("test");
        created.setEmail("test@test.cl");
        created.setPassword("test");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<CreatedUserDTO> users = userServicempl.getAllUsers();
        System.out.println(users);
        assertNotNull(users);
        assertEquals(Arrays.asList(created), users);
    }

    @Test
    void updateUser() {
        Phone phone = new Phone();
        phone.setUserId(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"));
        Set<Phone> phones = new HashSet<>();
        phones.add(phone);
        user.setPhones(phones);
        PhoneDTO phoneDTO = modelMapper.map(phone, PhoneDTO.class);
        UpdateUserDTO updateUser = new UpdateUserDTO();
        updateUser.setId(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"));
        updateUser.setName("test");
        updateUser.setEmail("test@test.cl");
        updateUser.setPassword("test");
        updateUser.setPhones(Arrays.asList(phoneDTO));
        when(userRepository.findById(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"))).thenReturn(Optional.of(user));
        when(modelMapper.map(phoneDTO, Phone.class)).thenReturn(phone);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, CreatedUserDTO.class)).thenReturn(created);
        CreatedUserDTO test = userServicempl.updateUser(updateUser);
        assertNotNull(test);
        assertEquals(test, created);
    }
}