package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.name like concat('%', ?1, '%')")
    Set<User> findAllByNameContaining(String name);

    User findByAccount(String Account);


}
