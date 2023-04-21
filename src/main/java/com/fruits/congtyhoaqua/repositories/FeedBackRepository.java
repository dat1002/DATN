package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface FeedBackRepository extends JpaRepository<Feedback, Integer> {
    Feedback findFeedbackById(Integer idFeedBack);

    @Query("select f from Feedback f where f.user = ?1")
    Set<Feedback> findAllByUser(User user);

}
