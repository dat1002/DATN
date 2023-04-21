//package com.fruits.congtyhoaqua.models;
//
//import com.fruits.congtyhoaqua.bases.BaseEntity;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.Nationalized;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.Set;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "bill-histories")
//public class BillHistory extends BaseEntity {
//
//    @Column(name = "code",unique = true)
//    @Nationalized
//    private String code;
//
//    @Column(name = "dateCreated")
//    private LocalDate dateCreated;
//
//    @Column(name = "name_user")
//    private String nameUser;
//
//    @Column(name = "id_user")
//    private Integer idUser;
//
//    @Column(name = "id_customer")
//    private Integer idCustomer;
//
//    @Column(name = "name_customer")
//    private Integer nameCustomer;
//
//    private Set<BillDetail> billDetails;
//}
