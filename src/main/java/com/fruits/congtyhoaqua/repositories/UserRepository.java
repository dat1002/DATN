package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.status=true and u.name like concat('%', ?1, '%') order by u.id")
    List<User> findAllByNameContaining(String name);

    @Query(nativeQuery = true, value = "select * from user where status = 'true' order by id")
    List<User> getAllUser();

//    lấy ra tất cả tài khoản của khách hàng
    @Query(nativeQuery = true, value = "select * from users LEFT JOIN user_role on user_role.users_id = users.id where roles_id='1' and status = 'true' order by users_id OFFSET ?1 rows fetch next ?2 rows only")
    List<User> selectAllUser(Integer start, Integer size);

    //    lấy ra tất cả tài khoản của khách hàng đã xóa
    @Query(nativeQuery = true, value = "select * from users LEFT JOIN user_role on user_role.users_id = users.id where roles_id='1' and status = 'false' order by users_id")
    List<User> selectAllUserDelete();

    //    lấy ra tất cả tài khoản của admin
    @Query(nativeQuery = true, value = "select * from users LEFT JOIN user_role on user_role.users_id = users.id where roles_id='2' and status = 'true' order by users_id")
    List<User> selectAllAdmin();
    User findByAccount(String Account);


}
