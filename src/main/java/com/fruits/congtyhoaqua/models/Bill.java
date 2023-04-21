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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    private Set<BillDetail> billDetails;

}
