package com.exam.portal.examModel.serviceImpl;

import com.exam.portal.examModel.entity.CategoryEntity;
import com.exam.portal.examModel.entity.QuestionEntity;
import com.exam.portal.examModel.entity.QuizEntity;
import com.exam.portal.examModel.repository.QuestionRepository;
import com.exam.portal.examModel.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public QuestionEntity create(QuestionEntity questionEntity) {
        return questionRepository.save(questionEntity);
    }

    @Override
    public QuestionEntity update(QuestionEntity questionEntity) {
        if (questionEntity.getId()!=null){
            Optional<QuestionEntity> persisted = questionRepository.findById(questionEntity.getId());
            if (persisted==null){
                return null;
            }
            QuestionEntity updated = questionRepository.save(questionEntity);
            return updated;
        }
        return null;
    }

    @Override
    public QuestionEntity delete(Long id) {
        Optional<QuestionEntity> optional= questionRepository.findById(id);
        if (optional.isPresent()) {
            questionRepository.deleteById(id);
            return optional.get();
        }
        return null;
    }

    @Override
    public Set<QuestionEntity> getAll() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public QuestionEntity getById(Long id) {
        Optional<QuestionEntity> optional= questionRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Set<QuestionEntity> findQuestionByQuizEntity(QuizEntity quizEntity) {
        return questionRepository.findByQuizEntity(quizEntity);
    }

    @Override
    public Set<QuestionEntity> findAllByQuizEntityId(Long id) {
        return questionRepository.findAllByQuizEntityId(id);
    }

//    @Override
//    public Set<QuestionEntity> getQuestionEntitiesOfQuizEntity(QuizEntity quizEntity) {
//        return questionRepository.findAllByQuizEntityId(quizEntity);
//    }


}
