package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Integer> {
    @Query("select m from Manufacture m where m.name like concat('%', ?1, '%') and m.status=true order by m.id" )
    Set<Manufacture> findAllByNameContaining(String name);

    @Query(nativeQuery = true, value = "select *  from manufactures  where status = 'true' order by id desc OFFSET ?1 rows fetch next ?2 rows only")
    List<Manufacture> selectAll(Integer start, Integer size);

}
