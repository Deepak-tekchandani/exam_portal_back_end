package com.exam.portal.examModel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "quiz")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String maxMarks;

    private String numberOfQuestions;

    private boolean active=false;

    //Relations
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "quizEntity" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<QuestionEntity>  questionEntities = new HashSet<>();


}
