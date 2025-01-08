package com.batuhan.eindprojectBatuhan.repository;

import com.batuhan.eindprojectBatuhan.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}