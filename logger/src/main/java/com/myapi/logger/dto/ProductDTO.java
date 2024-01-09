package com.myapi.logger.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    @NotBlank
    private Long id_user;
    @NotBlank
    private String name;
    @NotBlank
    private int price;
    @NotBlank
    private Date expirationDate;
}
