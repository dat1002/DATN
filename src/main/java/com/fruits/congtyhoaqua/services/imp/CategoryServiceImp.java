package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.CategoryDTO;
import com.fruits.congtyhoaqua.exceptions.BadRequestException;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Category;
import com.fruits.congtyhoaqua.repositories.CategoryRepository;
import com.fruits.congtyhoaqua.services.ICategoryService;
import com.fruits.congtyhoaqua.utils.Convert;
import com.fruits.congtyhoaqua.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UploadFile uploadFile;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category oldCategory = categoryRepository.findByName(categoryDTO.getName());
        if(oldCategory != null){
            throw  new BadRequestException("Duplicate Category.");
        }
        Category category =  new Category();
        Convert.fromCategoryDTOToCategory(categoryDTO,category);
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Integer idCategory, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if(category.isEmpty()){
            throw new NotFoundException("No Category");
        }
        return categoryRepository.save(Convert.fromCategoryDTOToCategory(categoryDTO,category.get()));
    }

    @Override
    public Category deleteCategory(Integer idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if (category.isEmpty()){
            throw new NotFoundException("No Category");
        }
        category.get().setStatus(false);
        categoryRepository.save(category.get());
        return category.get();
    }

    @Override
    public Category restoreCategory(Integer idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if (category.isEmpty()){
            throw new NotFoundException("No Category");
        }
        category.get().setStatus(true);
        categoryRepository.save(category.get());
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>(categoryRepository.findAll());
        if(categories.isEmpty()){
            throw new NotFoundException("No Category");
        }
        return categories;
    }

    @Override
    public Category editAvatarCategory(Integer idCategory, MultipartFile avatar) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if (category.isEmpty()){
            throw new NotFoundException("No fruit");
        }
        category.get().setAvatar(uploadFile.getUrlFromFile(avatar));
        return categoryRepository.save(category.get());
    }
}
