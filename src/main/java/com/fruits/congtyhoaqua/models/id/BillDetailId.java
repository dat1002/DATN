package com.fruits.congtyhoaqua.models.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class BillDetailId implements Serializable {

    @Column(name = "id_bill")
    private Integer idBill;

    @Column(name = "id_fruit")
    private Integer idFruit;

}
