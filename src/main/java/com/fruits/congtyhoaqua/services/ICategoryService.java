package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.CategoryDTO;
import com.fruits.congtyhoaqua.models.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category editCategory(Integer idCategory, CategoryDTO categoryDTO);
    Category deleteCategory(Integer idCategory);
    List<Category> getAllCategory();
    Category editAvatarCategory(Integer idCategory, MultipartFile avatar);

    Category restoreCategory(Integer idCategory);

}
