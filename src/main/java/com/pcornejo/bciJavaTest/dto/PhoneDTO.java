package com.pcornejo.bciJavaTest.dto;

import com.pcornejo.bciJavaTest.validation.ValidInteger;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {

    @NotBlank
    @ValidInteger
    private String number;

    @NotBlank
    @ValidInteger
    @JsonProperty(value = "citycode")
    private String cityCode;

    @NotBlank
    @ValidInteger
    @JsonProperty(value = "contrycode")
    private String contryCode;
}
