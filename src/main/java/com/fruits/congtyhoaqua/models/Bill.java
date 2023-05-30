package com.fruits.congtyhoaqua.models;

import com.fruits.congtyhoaqua.bases.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bills")
public class Bill extends BaseEntity {

    @Column(name = "code",unique = true)
    @Nationalized
    private String code;

    @Column(name = "dateCreated")
    private LocalDate dateCreated;

    @Column(name = "payment_method")
    @Nationalized
    private String paymentMethod;

    @Column(name = "payment_status")
    @Nationalized
    private String paymentStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    private Set<BillDetail> billDetails;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_order_status")
    private OrderStatus orderStatus;
}
