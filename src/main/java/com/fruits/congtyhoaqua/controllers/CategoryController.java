package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.CategoryDTO;
import com.fruits.congtyhoaqua.models.Category;
import com.fruits.congtyhoaqua.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController extends BaseController<Category> {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/create")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        return this.resSuccess(categoryService.createCategory(categoryDTO));
    }

    @PatchMapping("/edit-category/{idCategory}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> editCategory(@PathVariable Integer idCategory, @RequestBody CategoryDTO categoryDTO){
        return  this.resSuccess(categoryService.editCategory(idCategory, categoryDTO));
    }

    @DeleteMapping("delete-category/{idCategory}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer idCategory){
        return this.resSuccess(categoryService.deleteCategory(idCategory));
    }

    @GetMapping("/get-all-category")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllCategory(){
        return  this.resSetSuccess(categoryService.getAllCategory());
    }
}
