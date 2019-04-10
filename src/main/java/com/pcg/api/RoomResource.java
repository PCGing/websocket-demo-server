package com.pcg.api;

import com.pcg.dao.RoomRepository;
import com.pcg.entity.R;
import com.pcg.entity.Room;
import com.pcg.entity.User;
import com.pcg.util.CreateMD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName RoomResource
 * @AUTHOR 潘晨光
 * @DATE 2019/04/10 17:38
 **/
@Slf4j
@Component
@RequestMapping("/websocket/room")
public class RoomResource extends BaseApi{
    @Autowired
    RoomRepository roomRepository;

    /**
     * 注册
     */
    @ResponseBody
    @PostMapping("/register")
    public R register( @RequestBody Room room, HttpServletRequest request) {
        System.out.println (room);
        room.setStatus ( 1 );
        room.setCreateByTime ( new Date ( ) );
        Room r = roomRepository.save(room);
        if (r != null) {
            return ok (r);
        }
        return fail ("添加房间失败，请重试");
    }

    @ResponseBody
    @GetMapping("/findAll")
    public R all(HttpServletRequest request) {
        return ok (roomRepository.findByStatus(1));
    }

}
