package com.pcg.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Write By wangBin
 *
 * @Description: 
 *
 * @Date: 16:00 2018/12/7
**/

@Data
@Slf4j
@Entity(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String username;//用户名
    private String password;//密码
    private String nickname;//昵称
    private String email;   //邮箱
    private String phone;   //电话
    private Date registerTime;  //注册时间
    private int status = 0;    //状态  0:未验证
    private Long createBy;//创建者id
    private Date createByTime;//被创建时间

}
