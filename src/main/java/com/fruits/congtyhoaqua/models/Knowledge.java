package com.fruits.congtyhoaqua.models;

import com.fruits.congtyhoaqua.bases.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "knowledge")
public class Knowledge extends BaseEntity {
    @Column(name = "title")
    @Nationalized
    private String title;

    @Column(name = "avatar")
    @Nationalized
    private String avatar;

    @Column(name = "description")
    @Nationalized
    @Length(max = 5000)
    private String description;

    @Column(name = "created_by")
    @Nationalized
    private String created_by;
}
