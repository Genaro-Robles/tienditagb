package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.Exceptions.CategoryNotFoundException;
import com.integrador1.tienditagb.models.Category;
import com.integrador1.tienditagb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category newCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategorysActive() {
        return categoryRepository.findByStatus(true);
    }

    @Override
    public Category getCategorybyId(int id) {
        return categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));
    }

    @Override
    public Category updateCategory(Category newcategory, int id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(newcategory.getName());
                    category.setDescription(newcategory.getDescription());
                    return categoryRepository.save(category);
                }).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public String deleteCategory(int id) {
        categoryRepository.findById(id)
                .map(category -> {
                    category.setStatus(false);
                    return categoryRepository.save(category);
                }).orElseThrow(() -> new CategoryNotFoundException(id));
        return "Categoria con el "+id+" a sido eliminada";
    }

    @Override
    public String renewCategory(int id) {
        categoryRepository.findById(id)
                .map(category -> {
                    category.setStatus(true);
                    return categoryRepository.save(category);
                }).orElseThrow(() -> new CategoryNotFoundException(id));
        return "Categoria con el "+id+" a sido restaurada";
    }
}
