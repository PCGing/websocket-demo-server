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
     * 添加房间
     */
    @ResponseBody
    @PostMapping("/addRoom")
    public R addRoom( @RequestBody Room room, HttpServletRequest request) {
        System.out.println (room);
        room.setStatus ( 1 );
        room.setCreateByTime ( new Date ( ) );
        Room r = roomRepository.save(room);
        if (r != null) {
            return ok (r);
        }
        return fail ("添加房间失败，请重试");
    }

    /**
     * @Date 11:15 2019/04/11
     * @Param 查询所有房间
     * @return com.pcg.entity.R
    **/
    @ResponseBody
    @GetMapping("/findAll")
    public R all(HttpServletRequest request) {
        return ok (roomRepository.findByStatus(1));
    }

    @ResponseBody
    @GetMapping("/userIsRoom")
    public R userIsRoom(HttpServletRequest request,@RequestParam Long roomId, @RequestParam Long userId){
        Room room = roomRepository.findOne ( roomId );
        String talkUser = CreateMD5.convertMD5 (room.getTalkUser ());
        String[] userIds = talkUser.split ( "," );
        for (String user_id : userIds) {
            if ( user_id.equals ( userId.toString () ) ){
                System.out.println ("该房间有该用户");
            }
        }
        return ok ();
    }

}
