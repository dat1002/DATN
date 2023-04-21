package com.fruits.congtyhoaqua.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fruits.congtyhoaqua.bases.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")

public class Role extends BaseEntity{

    @Column(name = "name")
//    @Nationalized
    private String name;

    @ManyToMany
    @JoinTable( name = "user_role")
    @JsonIgnore
    private Set<User> users;

}
