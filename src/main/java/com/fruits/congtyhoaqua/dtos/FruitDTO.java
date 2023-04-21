package com.fruits.congtyhoaqua.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FruitDTO {
    @NotBlank(message = "name not null")
    private String name;


    @Length(max = 5000)
    private MultipartFile avatar;


    @Length(max = 5000)
    @Nationalized
    private String description;


    @NotBlank(message = "expiry not null")
    @Nationalized
    private String expiry;


    @NotBlank(message = "amount not null")
    @Nationalized
    private Double amount;



    @NotBlank(message = "price in not null")
    private Integer priceIn;


    @NotBlank(message = "price out not null")
    private Integer priceOut;

    private Integer sale;


    Set<Integer> idCategories;
}
