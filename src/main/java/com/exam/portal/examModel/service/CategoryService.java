package com.exam.portal.examModel.service;

import com.exam.portal.examModel.entity.CategoryEntity;

import java.util.Set;

public interface CategoryService {

    public CategoryEntity create(CategoryEntity categoryEntity);

    public CategoryEntity update(CategoryEntity categoryEntity);

    public CategoryEntity delete(Long id);

    public Set<CategoryEntity> getAll();

    public CategoryEntity getById(Long id);

}
