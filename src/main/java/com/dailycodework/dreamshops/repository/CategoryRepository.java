package com.dailycodework.dreamshops.repository;

import com.dailycodework.dreamshops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findByName(String name); // This method should return a Category object, not Object

  boolean existsByName(String name);
}
