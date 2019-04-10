package com.pcg.dao;

import com.pcg.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: Write By wangBin
 *
 * @Description:
 *
 * @Date: 15:57 2018/12/7
**/

public interface UserRepository extends PagingAndSortingRepository<User,Long>{

    @Query(value = "select * from t_user u where u.username=?1",nativeQuery = true)
    User findUserByName(String username);

    @Query(value = "select * from t_user u where u.email=?1",nativeQuery = true)
    User findUserByEmail(String email);

    @Query(value = "select * from t_user u where u.phone=?1",nativeQuery = true)
    User findUserByPhone(String phone);

    List<User> findByStatus(int status);

    User findByStatusAndId(int status, Long id);
}
