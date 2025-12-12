package com.example.eCom.service;

import com.example.eCom.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);

    String deleteCategory(int categoryId);

    String updateCategory(Category category, int categoryId);
}
