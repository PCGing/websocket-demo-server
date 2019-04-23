package com.pcg.api;

import com.pcg.dao.FriendRepository;
import com.pcg.dao.UserRepository;
import com.pcg.entity.Friend;
import com.pcg.entity.R;
import com.pcg.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FriendResource
 * @AUTHOR 潘晨光
 * @DATE 2019/04/18 14:38
 **/
@Slf4j
@Component
@RequestMapping("/websocket/friend")
public class FriendResource extends BaseApi{

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * @Date 14:44 2019/04/18
     * @Param 查询自己的所有好友
     * @return com.pcg.entity.R
    **/
    @ResponseBody
    @GetMapping("/findAll")
    public R all( HttpServletRequest request,@RequestParam("userId") Long userId) {

        List< Friend > friends = friendRepository.findByUserId(userId);
        List<User> userList = new ArrayList<>();
        friends.forEach ( e->{
            if (e.getUserId1().equals(userId)){
                userList.add(userRepository.findOne(e.getUserId2()));
            }else{
                userList.add(userRepository.findOne(e.getUserId1()));
            }
        });
        return ok (userList);
    }

}
