package com.example.eCom.service;

import com.example.eCom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    private int idx=1;


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(idx++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(int categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId()==categoryId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not Found"));

        if (category == null) {
            return "Category Not Found";
        }

        categories.remove(category);
        return "Category with categoryId: "+ categoryId + " deleted Successfully";
    }

    @Override
    public String updateCategory(Category category, int categoryId) {
        Category reqCategory = categories.stream()
                .filter(c -> c.getCategoryId()==categoryId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not Found"));

        reqCategory.setCategoryName(category.getCategoryName());
        return "Category with categoryId: " + categoryId + " updated Successfully";
    }
}
