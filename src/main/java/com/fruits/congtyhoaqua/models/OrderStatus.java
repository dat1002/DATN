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
@Table(name = "status_orders")
public class OrderStatus extends BaseEntity {
    @Column(name = "order_status")
    @Length(max = 5000)
    @Nationalized
    private String order_status;

    @OneToMany(mappedBy = "orderStatus",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Bill> bills ;
}
