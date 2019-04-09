package com.pcg.api;

import com.pcg.dao.UserRepository;
import com.pcg.entity.R;
import com.pcg.entity.User;
import com.pcg.util.CreateMD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Write By wangBin
 *
 * @Description: 用户实体类
 *
 * @Date: 15:54 2018/12/7
**/

@Slf4j
@Component
@RequestMapping("/websocket/user")
public class UserResource extends BaseApi {
    @Autowired
    UserRepository userRepository;


    /**
     * 注册
     */
    @ResponseBody
    @PostMapping("/register")
    public R register( @RequestBody User user, HttpServletRequest request) {
        System.out.println (user);
        String md5pwd = CreateMD5.getMd5(user.getPassword ());
        user.setPassword(md5pwd);
        user.setStatus(1);
        user.setCreateByTime(new Date());
        user.setNickname(user.getUsername());
//        User u = userRepository.save(user);
//        if (u != null) {
//            return ok (u);
//        }
        return fail ("注册失败，请重试");
    }

}
