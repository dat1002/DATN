package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Set<Bill> findAllByUser(User user);
    Bill findByCode(String code);
//    Set<Bill> findAllByDateCreatedBetween(LocalDate start, LocalDate end);
    List<Bill> findAllByDateCreatedBetween(LocalDate start, LocalDate end);

}
