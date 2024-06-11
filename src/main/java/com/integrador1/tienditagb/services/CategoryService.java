package com.integrador1.tienditagb.services;

import com.integrador1.tienditagb.models.Category;


import java.util.List;


public interface CategoryService {
    public Category newCategory(Category category);
    public List<Category> getAllCategory();
    public List<Category> getCategorysActive();
    public Category getCategorybyId(int id);
    public  Category updateCategory(Category category, int id);
    public String deleteCategory(int id);
    public String renewCategory(int id);
}
