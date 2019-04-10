package com.pcg.api;

import com.pcg.dao.UserRepository;
import com.pcg.entity.R;
import com.pcg.entity.User;
import com.pcg.util.CreateMD5;
import com.pcg.util.DataTablePageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

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
        User u = userRepository.save(user);
        if (u != null) {
            return ok (u);
        }
        return fail ("注册失败，请重试");
    }

    /**
     * 查询用户名是否存在
     */
    @ResponseBody
    @GetMapping("/validUsername")
    public boolean validUsername(@RequestParam("username") String username) {
        if (username == null && "".equals(username)){
            return false;
        }
        try {
            User userByName = userRepository.findUserByName(username);
            if (userByName == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }

    /**
     * 查询手机号是否存在
     */
    @ResponseBody
    @GetMapping("/validPhone")
    public boolean validPhone(@RequestParam("phone") String phone) {
        if (phone == null && "".equals(phone)){
            return false;
        }
        try {
            User userByName = userRepository.findUserByPhone(phone);
            if (userByName == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }

    /**
     * 查询邮箱是否存在
     */
    @ResponseBody
    @GetMapping("/validEmail")
    public boolean validEmail(@RequestParam("email") String email) {
        if (email == null && "".equals(email)){
            return false;
        }
        try {
            User userByName = userRepository.findUserByEmail(email);
            if (userByName == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }

    /**
     * 用户列表
     */
    @ResponseBody
    @GetMapping("/list")
    public DataTablePageUtil getList( HttpServletRequest request) {
        DataTablePageUtil dataTablePageUtil = new DataTablePageUtil(request);
        String search = dataTablePageUtil.getSearch();
        dataTablePageUtil.setSearchStr(search);
        dataTablePageUtil.setColumns_name("role_name,email,phone");

        if (search.indexOf("\'") != -1) {
            search = search.replace("\'", "");
        }
        StringBuilder sb = new StringBuilder("SELECT u.*, " +
                "FROM t_user u ");
//        if (StringUtils.isNotBlank(search)) {
        sb.append("WHERE 1 = 1 ");
        sb.append(dataTablePageUtil.getSearchStr());
//        }
        sb.append(" GROUP BY u.id ORDER BY u.id ASC");

        String countSql = "select count(*) totalNum from (" + sb.toString() + ") t";

        String start = request.getParameter("start");
        String length = request.getParameter("length");
        sb.append(" LIMIT " + start + "," + length);

        List rows = jdbcTemplate.queryForList(sb.toString());
        dataTablePageUtil.setData(rows);
        int count = Integer.parseInt(jdbcTemplate.queryForList(countSql).get(0).get("totalNum").toString());
        dataTablePageUtil.setRecordsTotal(count);
        dataTablePageUtil.setRecordsFiltered(count);

        return dataTablePageUtil;
    }

    /**
     * 用户列表（不分页）
     */
    @ResponseBody
    @GetMapping("/findAll")
    public R all(HttpServletRequest request) {
        return ok (userRepository.findByStatus(1));
    }

    /**
     * 根据id查找用户
     */
    @ResponseBody
    @GetMapping("/findById/{userId}")
    public R findById(HttpServletRequest request, @PathVariable("userId")Long userId) {
        log.debug("查看参数====="+userId);
        User user = userRepository.findByStatusAndId(1,userId);
        return ok(user);
    }

    /**
     * 登陆
     */
    @ResponseBody
    @PostMapping("/login")
    public R login(@RequestParam("username")String username,@RequestParam("password")String password) {
        try {
            if (username == null || "".equals(username)) {
                return fail ("请输入用户名");
            }
            if (password == null || "".equals(password)) {
                return fail ("请输入密码");
            }
            User u = userRepository.findUserByName(username);
            if (u == null) {
                return fail ("该用户不存在");
            }

            u.setPassword ( "" );
            return ok(u);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return fail (e.getMessage());
        }
    }

}
