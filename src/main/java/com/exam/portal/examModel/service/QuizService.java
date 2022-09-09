package com.exam.portal.examModel.service;

import com.exam.portal.examModel.entity.QuizEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {

    public QuizEntity create(QuizEntity quizEntity);

    public QuizEntity update(QuizEntity quizEntity);

    public QuizEntity delete(Long id);

    public Set<QuizEntity> getAll();

    public QuizEntity getById(Long id);

    List<QuizEntity> findAllByCategoryEntityId(Long id);
}
