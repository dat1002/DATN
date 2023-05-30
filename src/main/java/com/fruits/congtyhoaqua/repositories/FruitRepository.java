package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Category;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.Manufacture;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Repository
public interface FruitRepository extends JpaRepository<Fruit, Integer> {

    List<Fruit> findAllByDateCreatedBetween(LocalDate afterDate,LocalDate beforeDate);

    @Query("select f from Fruit f where f.status = true and f.name like concat('%', ?1, '%') order by f.id")
    List<Fruit> findAllByNameContaining(String name);

    @Query(nativeQuery = true, value = "select fruits.id, fruits.created_at,fruits.status, fruits.updated_at, fruits.amount, fruits.avatar, fruits.date_created, fruits.description, fruits.expiry, fruits.name, fruits.price_in, fruits.price_out, fruits.id_manufacture, fruits.sale from fruits LEFT JOIN fruit_category on fruits.id = fruit_category.id_fruit join categories on fruit_category.id_category = categories.id where fruits.status='true' and categories.status='true' order by fruits.id desc OFFSET ?1 rows fetch next ?2 rows only")
    List<Fruit> selectAllFruit(Integer start, Integer size);

    @Query(nativeQuery = true, value = "select fruits.id, fruits.created_at,fruits.status, fruits.updated_at, fruits.amount, fruits.avatar, fruits.date_created, fruits.description, fruits.expiry, fruits.name, fruits.price_in, fruits.price_out, fruits.id_manufacture, fruits.sale from fruits LEFT JOIN fruit_category on fruits.id = fruit_category.id_fruit join categories on fruit_category.id_category = categories.id where fruits.status='false' or categories.status='false' order by fruits.id")
    List<Fruit> selectAllFruitDeleted();

//    Set<Fruit> findAllByNameContains(String name);

    @Query("select f from Fruit f where f.status = true and f.manufacture = ?1 order by f.id")
    List<Fruit> findAllByManufactureAndOrOrderById(Manufacture manufacture);

    @Query("select f from Fruit f where f.status = true and f.priceOut > ?1 and f.priceOut < ?2 order by f.id")
    List<Fruit> findAllByPriceOutGreaterThanAndPriceOutLessThan(Integer greaterPrice, Integer lessPrice);

    @Query(nativeQuery = true, value = "select top(4) *  from fruits  where status = 'true' order by sale desc")
    List<Fruit> selectTopSale();

    @Query(nativeQuery = true, value = "select top(4) *  from fruits  where status = 'true' order by created_at desc")
    List<Fruit> selectTopNew();

    @Query(nativeQuery = true, value = "select top(4) *  from fruits  where status = 'true' order by sale*price_out desc")
    List<Fruit> selectTopSaleVip();

    @Query(nativeQuery = true, value = "select top(4) fruits.id, fruits.created_at,fruits.status, fruits.updated_at, fruits.amount, fruits.avatar, fruits.date_created, fruits.description, fruits.expiry, fruits.name, fruits.price_in, fruits.price_out, fruits.id_manufacture, fruits.sale, sum(bill_detail.amount) as total from fruits join bill_detail on fruits.id = bill_detail.id_fruit join bills on bills.id = bill_detail.id_bill WHERE fruits.status ='true' and bills.status ='true' GROUP BY fruits.id, fruits.created_at,fruits.status, fruits.updated_at, fruits.amount, fruits.avatar, fruits.date_created, fruits.description, fruits.expiry, fruits.name, fruits.price_in, fruits.price_out, fruits.id_manufacture, fruits.sale  ORDER BY total DESC")
    List<Fruit> top5();

}
