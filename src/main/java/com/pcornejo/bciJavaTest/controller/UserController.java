package com.pcornejo.bciJavaTest.controller;
import com.pcornejo.bciJavaTest.dto.CreateUserDTO;
import com.pcornejo.bciJavaTest.dto.CreatedUserDTO;
import com.pcornejo.bciJavaTest.dto.UpdateUserDTO;
import com.pcornejo.bciJavaTest.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(
        value = "api/users"
)
@Validated
public class UserController {

    private UserService userService;

    @PostMapping()
    public ResponseEntity<CreatedUserDTO> createUser(@Valid @RequestBody CreateUserDTO userDto) throws Exception {
        CreatedUserDTO savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CreatedUserDTO> getUserById(@Valid @PathVariable("id") UUID userId) throws Exception {
        CreatedUserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreatedUserDTO>> getAllUsers() throws Exception {
        List<CreatedUserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CreatedUserDTO> updateUser(@Valid @PathVariable("id") UUID userId, @Valid @RequestBody UpdateUserDTO user) throws Exception {
        user.setId(userId);
        CreatedUserDTO updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable("id") UUID userId) throws Exception {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }
}
