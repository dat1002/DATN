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

    @Query(nativeQuery = true, value = "select * from fruits where status = 'true' order by id")
    List<Fruit> selectAllFruit();

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



}
