package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    @Query("select c from Category c where c.id in ?1")
    Set<Category> findAllByIdIn(Set<Integer> idCategories);
}
