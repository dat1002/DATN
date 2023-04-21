package com.fruits.congtyhoaqua.models.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartDetailId implements Serializable {
    @Column(name = "id_cart")
    private Integer idCart;

    @Column(name = "id_fruit")
    private Integer idFruit;
}
