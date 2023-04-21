package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.CategoryDTO;
import com.fruits.congtyhoaqua.models.Category;

import java.util.Set;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category editCategory(Integer idCategory, CategoryDTO categoryDTO);
    Category deleteCategory(Integer idCategory);
    Set<Category> getAllCategory();
}
