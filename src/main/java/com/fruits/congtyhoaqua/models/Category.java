package com.fruits.congtyhoaqua.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fruits.congtyhoaqua.bases.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "name")
    @Nationalized
    private String name;

    @Column(name = "description")
    @Nationalized
    @Length(max = 5000)
    private String description;

    @Column(name = "avatar")
    @Nationalized
    private String avatar;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FruitCategory> fruitCategories;
}
