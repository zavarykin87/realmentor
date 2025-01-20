package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
