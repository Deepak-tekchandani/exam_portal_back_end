package com.exam.portal.examModel.serviceImpl;

import com.exam.portal.examModel.entity.QuestionEntity;
import com.exam.portal.examModel.entity.QuizEntity;
import com.exam.portal.examModel.repository.QuizRepository;
import com.exam.portal.examModel.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public QuizEntity create(QuizEntity quizEntity) {
        return quizRepository.save(quizEntity);
    }

    @Override
    public QuizEntity update(QuizEntity quizEntity) {
        if (quizEntity.getId()!=null){
            Optional<QuizEntity> persisted = quizRepository.findById(quizEntity.getId());
            if (persisted==null){
                return null;
            }
            QuizEntity updated = quizRepository.save(quizEntity);
            return updated;
        }
        return null;
    }

    @Override
    public QuizEntity delete(Long id) {
        Optional<QuizEntity> optional= quizRepository.findById(id);
        if (optional.isPresent()) {
            quizRepository.deleteById(id);
            return optional.get();
        }
        return null;
    }

    @Override
    public Set<QuizEntity> getAll() {
        return new HashSet<>(quizRepository.findAll());
    }

    @Override
    public QuizEntity getById(Long id) {
        Optional<QuizEntity> optional= quizRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<QuizEntity> findAllByCategoryEntityId(Long id) {
        return quizRepository.findAllByCategoryEntityId(id);
    }
}
