package com.fruits.congtyhoaqua.models;

import com.fruits.congtyhoaqua.models.id.FruitCategoryId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FruitCategory {
    @EmbeddedId
    private FruitCategoryId fruitCategoryId;

    @ManyToOne
    @MapsId("fruitId")
    @JoinColumn(name = "id_fruit")
    private Fruit fruit;


    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "id_category")
    private Category category;
}
