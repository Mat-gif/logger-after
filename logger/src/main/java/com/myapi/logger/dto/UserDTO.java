package com.myapi.logger.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private String name;
    @NotBlank
    private int age;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
