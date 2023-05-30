package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.BillDetail;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.id.BillDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, BillDetailId> {
    @Query("select f from BillDetail f where f.billDetailId.idBill = ?1 ")
    List<BillDetail> findAllByIdBill(Integer idBill);
}
