package com.fruits.congtyhoaqua.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fruits.congtyhoaqua.bases.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "fruits")
public class Fruit extends BaseEntity {

    @Column(name = "name")
    @Nationalized
    private String name;

    @Column(name = "avatar")
    @Nationalized
    private String avatar;

    @Column(name = "description")
    @Length(max = 5000)
    @Nationalized
    private String description;

    @Column(name = "expiry")
    private LocalDate expiry;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "price_in")
    private Integer priceIn;

    @Column(name = "priceOut")
    private Integer priceOut;

    @Column(name = "sale")
    private Integer sale;

    @Column(name = "dateCreated")
    private LocalDate dateCreated;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_manufacture")
    @Nationalized
    private Manufacture manufacture;

    @OneToMany(mappedBy = "fruit",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FruitCategory> fruitCategories;

    @OneToMany(mappedBy = "fruit",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BillDetail> billDetails;

    @OneToMany(mappedBy = "fruit",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CartDetail> cartDetails;
}
