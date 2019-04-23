package com.pcg.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName 好友表
 * @AUTHOR 潘晨光
 * @DATE 2019/04/18 14:34
 **/

@Data
@Slf4j
@Entity(name = "t_friend")
public class Friend implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long userId1;
    private Long userId2;
    private String tags;
    private Date registerTime;
    private int status = 0;
    private Long createBy;
    private Date createByTime;
}
