package com.exam.portal.examModel.repository;

import com.exam.portal.examModel.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizEntity ,Long> {

    List<QuizEntity> findAllByCategoryEntityId(Long id);
}
