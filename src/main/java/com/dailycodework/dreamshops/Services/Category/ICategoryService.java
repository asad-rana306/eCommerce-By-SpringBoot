package com.dailycodework.dreamshops.Services.Category;

import com.dailycodework.dreamshops.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);
}
