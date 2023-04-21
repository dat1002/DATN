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
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "account",unique = true)
    @NotBlank
    @Nationalized
    private String account;

    @Column(name = "password")
    @NotBlank
    @Nationalized
    private String password;

    @Column(name = "pre_name")
    @Nationalized
    @NotBlank
    private String preName;

    @Column(name = "name")
    @Nationalized
    @NotBlank
    private String name;
    
    @Column(name = "age")

    private Integer age;

    @Column(name = "address")
    @NotBlank
    @Length(max = 5000)
    @Nationalized
    private String address;

    @Column(name = "birthday")
    @NotBlank
    @Nationalized
    private String birthday;

    @Column(name = "avatar")
    @Length(max = 5000)
    private String avatar;


    @Column(name = "phoneNumber")
    @NotBlank
    @Nationalized
    private String phoneNumber;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private  Set<Bill> bills;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private  Set<Cart> carts;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "id_user",referencedColumnName = "id_user"),
//            inverseJoinColumns = @JoinColumn(name = "id_role",referencedColumnName = "id_role"))
////    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Feedback> feedback;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", preName='" + preName + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bills=" + bills +
                ", roles=" + roles +
                '}';
    }
}
