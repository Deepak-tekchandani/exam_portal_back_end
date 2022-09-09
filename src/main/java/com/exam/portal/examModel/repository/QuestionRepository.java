package com.exam.portal.examModel.repository;

import com.exam.portal.examModel.entity.QuestionEntity;
import com.exam.portal.examModel.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<QuestionEntity , Long> {
    Set<QuestionEntity> findByQuizEntity(QuizEntity quizEntity);

//    Set<QuestionEntity> getQuestionEntitiesOfQuizEntity(QuizEntity quizEntity);
//
    Set<QuestionEntity> findAllByQuizEntityId(Long id);
}
