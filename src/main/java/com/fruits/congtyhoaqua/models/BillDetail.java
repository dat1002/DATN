package com.fruits.congtyhoaqua.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fruits.congtyhoaqua.models.id.BillDetailId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillDetail {

    @EmbeddedId
    private BillDetailId billDetailId;

    @ManyToOne
    @MapsId("idBill")
    @JoinColumn(name = "id_bill")
    @JsonIgnore
    private Bill bill;

    @ManyToOne
    @MapsId("idFruit")
    @JoinColumn(name = "id_fruit")
    private Fruit fruit;

    private Integer amount;
}
