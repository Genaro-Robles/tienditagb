package com.integrador1.tienditagb.controllers;

import com.integrador1.tienditagb.models.Category;
import com.integrador1.tienditagb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public String newCategory(@RequestBody Category category){
        try {
            categoryService.newCategory(category);
            return "Nueva categoria ingresada con exito";
        } catch (Exception ex) {
            return "Error: "+ex;
        }
    }
    @GetMapping
    public List<Category> getAllCategorys(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/active")
    public List<Category> getCategorysActive(){
        return categoryService.getCategorysActive();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id){
        return categoryService.getCategorybyId(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable int id){
        return  categoryService.updateCategory(category,id);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }

    @PatchMapping("/{id}")
    public String renewCategory(@PathVariable int id){
        return categoryService.renewCategory(id);
    }
}
