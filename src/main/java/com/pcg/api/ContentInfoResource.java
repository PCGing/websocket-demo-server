package com.pcg.api;

import com.pcg.dao.ContentInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

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
    ContentInfoRepository contentInfoRepository;

}
