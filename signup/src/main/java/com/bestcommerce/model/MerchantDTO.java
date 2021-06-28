package com.bestcommerce.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDTO {

    @NotBlank(message = "*Name cannot be empty")
    private String name;


//  @Pattern(regexp = "'^(?=.*[A-Za-z])(?=.*\\d)$'",message = "Password should be alphanumeric")
    @Size(min = 6,max = 20,message = "Password should be min 6 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}",message = "Password should be alphanumeric")
    private String password;

    private String ownerName;

    private String address;

    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private MerchantType merchantType;

}
