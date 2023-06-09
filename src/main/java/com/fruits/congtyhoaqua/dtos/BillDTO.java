package com.fruits.congtyhoaqua.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    @Nationalized
    private String code;

    @Nationalized
    private String paymentMethod;

    @Nationalized
    private String paymentStatus;

}
