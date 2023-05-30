package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Feedback;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KnowledgeRepository extends JpaRepository<Knowledge, Integer> {

    @Query(nativeQuery = true, value = "select * from knowledge where knowledge.status='true' order by id desc ")
    List<Knowledge> selectAllKnowledge();
}
