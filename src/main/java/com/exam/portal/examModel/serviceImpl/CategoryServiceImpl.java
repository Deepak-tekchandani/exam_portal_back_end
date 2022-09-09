package com.exam.portal.examModel.serviceImpl;

import com.exam.portal.examModel.entity.CategoryEntity;
import com.exam.portal.examModel.repository.CategoryRepository;
import com.exam.portal.examModel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        return this.categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {
        if (categoryEntity.getId()!=null){
            Optional<CategoryEntity> persisted = categoryRepository.findById(categoryEntity.getId());
            if (persisted==null){
                return null;
            }
            CategoryEntity updated = categoryRepository.save(categoryEntity);
            return updated;
        }
        return null;
    }

    @Override
    public CategoryEntity delete(Long id) {
        Optional<CategoryEntity> optional= categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.deleteById(id);
            return optional.get();
        }
        return null;
    }

    @Override
    public Set<CategoryEntity> getAll() {
        return new LinkedHashSet<>(categoryRepository.findAll());
    }

    @Override
    public CategoryEntity getById(Long id) {
        Optional<CategoryEntity> optional= categoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
