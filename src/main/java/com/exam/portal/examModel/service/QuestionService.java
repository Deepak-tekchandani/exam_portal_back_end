package com.exam.portal.examModel.service;

import com.exam.portal.examModel.entity.QuestionEntity;
import com.exam.portal.examModel.entity.QuizEntity;

import java.util.Set;

public interface QuestionService {

    public QuestionEntity create(QuestionEntity questionEntity);

    public QuestionEntity update(QuestionEntity questionEntity);

    public QuestionEntity delete(Long id);

    public Set<QuestionEntity> getAll();

    public QuestionEntity getById(Long id);

    Set<QuestionEntity> findQuestionByQuizEntity(QuizEntity quizEntity);

    Set<QuestionEntity> findAllByQuizEntityId(Long id);
}
