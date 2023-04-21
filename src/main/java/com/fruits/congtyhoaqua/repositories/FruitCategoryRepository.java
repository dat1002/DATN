package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.FruitCategory;
import com.fruits.congtyhoaqua.models.id.FruitCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitCategoryRepository extends JpaRepository<FruitCategory, FruitCategoryId> {
}
