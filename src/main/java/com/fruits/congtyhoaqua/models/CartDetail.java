package com.fruits.congtyhoaqua.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fruits.congtyhoaqua.bases.BaseEntity;
import com.fruits.congtyhoaqua.models.id.CartDetailId;
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
public class CartDetail {

    @EmbeddedId
    private CartDetailId cartDetailId;

    @ManyToOne
    @MapsId("idCart")
    @JoinColumn(name = "id_cart")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @MapsId("idFruit")
    @JoinColumn(name = "id_fruit")
    @JsonIgnore

    private Fruit fruit;

    private Integer amount;
//    @ManyToOne
//    @MapsId("idBill")
//    @JoinColumn(name = "id_bill")
//    @JsonIgnore
//    private Bill bill;
//
//    @ManyToOne
//    @MapsId("idFruit")
//    @JoinColumn(name = "id_fruit")
//    private Fruit fruit;
//
//    private Integer amount;

}
