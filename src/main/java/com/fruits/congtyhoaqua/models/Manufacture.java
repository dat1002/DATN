package com.fruits.congtyhoaqua.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fruits.congtyhoaqua.bases.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manufactures")
public class Manufacture extends BaseEntity {
    @Column(name = "name")
    @Nationalized
    @Length(max = 5000)
    private String name;

    @Column(name = "address")
    @Nationalized
    @Length(max = 5000)
    private String address;

    @Column(name = "phone_number")
    @Nationalized
    private String phoneNumber;

    @OneToMany(mappedBy = "manufacture",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Fruit> fruits ;


}
