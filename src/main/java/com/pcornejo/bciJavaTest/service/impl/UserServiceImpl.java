package com.pcornejo.bciJavaTest.service.impl;

import com.pcornejo.bciJavaTest.dto.CreateUserDTO;
import com.pcornejo.bciJavaTest.dto.CreatedUserDTO;
import com.pcornejo.bciJavaTest.dto.UpdateUserDTO;
import com.pcornejo.bciJavaTest.entity.Phone;
import com.pcornejo.bciJavaTest.entity.User;
import com.pcornejo.bciJavaTest.exception.EmailAlreadyExistException;
import com.pcornejo.bciJavaTest.exception.UserNotFoundException;
import com.pcornejo.bciJavaTest.repository.UserRepository;
import com.pcornejo.bciJavaTest.service.UserService;
import com.pcornejo.bciJavaTest.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public CreatedUserDTO createUser(CreateUserDTO userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistException("El correo ya registrado");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setToken(jwtUtil.generateAccessToken(user));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, CreatedUserDTO.class);
    }

    @Override
    public CreatedUserDTO getUserById(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        System.out.println(optionalUser);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("El usuario no existe");
        }
        User existingUser = optionalUser.get();
        return modelMapper.map(existingUser, CreatedUserDTO.class);
    }

    @Override
    public List<CreatedUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, CreatedUserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CreatedUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(updateUserDTO.getId());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("El usuario no existe");
        }

        User existingUser = optionalUser.get();
        existingUser.setName(updateUserDTO.getName());
        existingUser.setEmail(updateUserDTO.getEmail());
        existingUser.setPassword(updateUserDTO.getPassword());
        Set<Phone> phones = updateUserDTO.getPhones().stream().map(
                (phoneDTO) -> modelMapper.map(phoneDTO, Phone.class))
                .collect(Collectors.toSet());
        phones.forEach((phone) -> {
            phone.setUserId(existingUser.getId());
        });
        existingUser.getPhones().clear();
        existingUser.getPhones().addAll(phones);

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, CreatedUserDTO.class);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
