package com.fruits.congtyhoaqua.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {
    @Length(max = 5000)
    @Nationalized
    private String orderStatus;

}
