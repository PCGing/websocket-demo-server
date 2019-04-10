package com.pcg.websocket;

import com.pcg.api.BaseApi;
import com.pcg.entity.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CheckCenterController
 * @AUTHOR 潘晨光
 * @DATE 2019/04/08 21:29
 **/
@Controller
@RequestMapping("/websocket")
public class CheckCenterController extends BaseApi {

    //推送数据接口
    @ResponseBody
    @RequestMapping("/sockjs/push/{cid}")
    public R pushToWeb( @PathVariable String cid, String message) throws Exception {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return fail( cid+"#"+e.getMessage());
        }
        return ok(cid);
    }
}
