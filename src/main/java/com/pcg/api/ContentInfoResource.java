package com.pcg.api;

import com.pcg.dao.ContentInfoRepository;
import com.pcg.entity.ContentInfo;
import com.pcg.entity.R;
import com.pcg.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ContentInfoResource
 * @AUTHOR 潘晨光
 * @DATE 2019/04/11 10:58
 **/
@Slf4j
@Component
@RequestMapping("/websocket/contentInfo")
public class ContentInfoResource extends BaseApi{

    @Autowired
    private ContentInfoRepository contentInfoRepository;



    /**
     * 添加
     */
    @ResponseBody
    @GetMapping("/findContentInfo")
    public R save(@RequestParam Long fromUserId, @RequestParam Long toUserId){

        List<Map<String, Object>> list = jdbcTemplate.queryForList ( "SELECT *,(SELECT nickname FROM t_user WHERE id=t_content_info.from_user_id) as name " +
                " FROM t_content_info WHERE status = 1 AND ((from_user_id="+fromUserId+" AND to_user_id="+toUserId+") " +
                "OR (from_user_id="+toUserId+" AND to_user_id="+fromUserId+"))" );
        return ok (list);
    }


}
