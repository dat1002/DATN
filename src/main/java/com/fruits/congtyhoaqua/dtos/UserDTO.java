package com.fruits.congtyhoaqua.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "account not null")
    @Nationalized
    private String account;


    @NotBlank(message = "password not null")
    @Nationalized
    private String password;


    @NotBlank(message = "pre name not null")
    @Nationalized
    private String preName;


    @NotBlank(message = "name not null")
    @Nationalized
    private String name;


    private Integer age;


    @Length(max = 5000)
    @Nationalized
    private String address;


    @NotBlank(message = "birthday not null")
    @Nationalized
    private String birthday;


//    @Length(max = 200000)
//    @Nationalized
    private MultipartFile avatar;


    @NotBlank(message = "phone number not null")
    @Nationalized
    private String phoneNumber;
}
