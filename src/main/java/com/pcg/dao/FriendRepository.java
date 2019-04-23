package com.pcg.dao;

import com.pcg.entity.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @InferfaceName FriendRepository
 * @AUTHOR 潘晨光
 * @DATE 2019/04/18 14:38
 **/
public interface FriendRepository extends PagingAndSortingRepository < Friend,Long> {

    @Query(value = "select * from t_friend where status=1 and (user_id1=?1 or user_id2=?1)" ,nativeQuery = true)
    List< Friend> findByUserId ( Long userId );
}
