package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query(nativeQuery = true, value = "select * from bills  order by bills.id desc OFFSET ?1 rows fetch next ?2 rows only")
    List<Bill> selectAllBill(Integer start, Integer size);

//    @Query(nativeQuery = true, value = "select * from bills  order by bills.id desc ")
//    List<Bill> selectAllBill();

    @Query(nativeQuery = true, value = "select * from bills where status = 'false' order by bills.id")
    List<Bill> selectAllBillCancelled();

    @Query(nativeQuery = true, value = "select * from bills  where bills.id_user = ?1 order by id desc OFFSET ?2 rows fetch next ?3 rows only")
    List<Bill> findAllByUser(Integer idUser,Integer start, Integer size);
    Bill findByCode(String code);
//    Set<Bill> findAllByDateCreatedBetween(LocalDate start, LocalDate end);
    List<Bill> findAllByDateCreatedBetween(LocalDate start, LocalDate end);

    @Query(nativeQuery = true, value = "select sum((fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where CAST(GETDATE() AS DATE) = bills.date_created and bills.status='true'")
    Double dayRevenue();

    @Query(nativeQuery = true, value = "select sum(CONVERT(Float,(fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount)) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where bills.date_created >= DATEADD(day, -7, GETDATE()) and bills.status='true'")
    Double weeklyRevenue();

    @Query(nativeQuery = true, value = "select sum(CONVERT(Float,(fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount)) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where bills.date_created >= DATEADD(month, -1, GETDATE()) and bills.status='true'")
    Double monthlyRevenue();

    @Query(nativeQuery = true, value = "select sum(CONVERT(Float,(fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount)) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where bills.date_created >= DATEADD(year, -1, GETDATE()) and bills.status='true'")
    Double annualRevenue();

    @Query(nativeQuery = true, value = "select sum((fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount - fruits.price_in*bill_detail.amount) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where CAST(GETDATE() AS DATE) = bills.date_created and bills.status='true'")
    Double dayProfit();

    @Query(nativeQuery = true, value = "select sum(CONVERT(Float,(fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount - fruits.price_in*bill_detail.amount)) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where bills.date_created >= DATEADD(day, -7, GETDATE()) and bills.status='true'")
    Double weeklyProfit();

    @Query(nativeQuery = true, value = "select sum(CONVERT(Float,(fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount - fruits.price_in*bill_detail.amount)) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where bills.date_created >= DATEADD(month, -1, GETDATE()) and bills.status='true'")
    Double monthlyProfit();

    @Query(nativeQuery = true, value = "select sum(CONVERT(Float,(fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount - fruits.price_in*bill_detail.amount)) from bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id where bills.date_created >= DATEADD(year, -1, GETDATE()) and bills.status='true'")
    Double annualProfit();



//    @Query(nativeQuery = true, value = "SELECT sum(CONVERT(Float,(fruits.price_out-fruits.price_out*sale/100)*bill_detail.amount)) as 'annual' FROM bills join bill_detail on bills.id = bill_detail.id_bill join fruits on bill_detail.id_fruit = fruits.id WHERE YEAR(bills.created_at) = '2023' GROUP BY MONTH(bills.created_at) ORDER BY MONTH(bills.created_at)")
//    List<Float> annual();



}
