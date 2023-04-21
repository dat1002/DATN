package com.fruits.congtyhoaqua.models.id;

import com.fruits.congtyhoaqua.models.Fruit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FruitCategoryId implements Serializable {

    @Column(name = "id_fruit")
    private Integer fruitId;


    @Column(name = "id_category")
    private Integer categoryId;

}
