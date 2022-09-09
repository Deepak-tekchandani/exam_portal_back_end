package com.exam.portal.examModel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    //Relations

    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<QuizEntity> quizzes = new LinkedHashSet<>();
}
