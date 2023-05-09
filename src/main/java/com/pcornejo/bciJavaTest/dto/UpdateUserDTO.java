package com.pcornejo.bciJavaTest.dto;

import com.pcornejo.bciJavaTest.validation.ValidEmail;
import com.pcornejo.bciJavaTest.validation.ValidPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @ValidEmail(message = "{email.message}")
    private String email;

    @NotBlank
    @ValidPassword(message = "{password.message}")
    private String password;

    @Valid
    @NotNull
    private List<PhoneDTO> phones;
}
