package com.fruits.congtyhoaqua.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeDTO {
    @Nationalized
    private String title;

    @Length(max = 5000)
    private MultipartFile avatar;

    @Length(max = 5000)
    @Nationalized
    private String description;

    @Nationalized
    private String created_by;
}
