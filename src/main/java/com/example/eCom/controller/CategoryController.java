package com.example.eCom.controller;

import com.example.eCom.model.Category;
import com.example.eCom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {
//    private List<Category> categories =  new ArrayList<>();

    @Autowired
    private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping ("api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories =  categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping ("/api/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>( "Category created successfully!" , HttpStatus.CREATED);
    }

    @DeleteMapping ("api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
        try{
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping ("api/admin/updateCategory/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable int categoryId) {
        try{
            String update = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>(update, HttpStatus.OK);
        }catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
