package com.pcg.dao;

import com.pcg.entity.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @InferfaceName RoomRepository
 * @AUTHOR 潘晨光
 * @DATE 2019/04/10 17:38
 **/
public interface RoomRepository extends PagingAndSortingRepository < Room,Long> {
    List<Room> findByStatus ( int status );
}
