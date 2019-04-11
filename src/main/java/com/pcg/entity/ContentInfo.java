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
 * @ClassName Room
 * @AUTHOR 潘晨光
 * @DATE 2019/04/10 17:26
 **/
@Data
@Slf4j
@Entity(name = "t_content_info")
public class ContentInfo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long fromUserId;    //发送人Id
    private Long toUserId;      //接收人Id
    private Integer status;
    private Long createBy;//创建者id
    private Date createByTime;//被创建时间
    private Long roomId;

}
