package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Integer> {
    @Query("select m from Manufacture m where m.name like concat('%', ?1, '%')")
    Set<Manufacture> findAllByNameContaining(String name);
}
