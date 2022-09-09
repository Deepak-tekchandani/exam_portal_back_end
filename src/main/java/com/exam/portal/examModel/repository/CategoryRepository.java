package com.exam.portal.examModel.repository;

import com.exam.portal.examModel.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity , Long> {
}
