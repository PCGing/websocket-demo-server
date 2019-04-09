package com.pcg.util;

import java.util.Map;

/**
 * Created by surfingren on 2018/1/17.
 */
public class ParamUtils {

    static String checkAndGetParam(Map<String, Object> map, String param) {
        String description = null;
        if (map.get(param) != null) {
            description = map.get(param) + "";
        }
        return description;
    }

    static String checkAndGetParam(Map<String, Object> map, String param, Boolean canNull) throws Exception {
        return checkAndGetParam(map, param, canNull , null);
    }

    static String checkAndGetParam(Map<String, Object> map, String param, Boolean canNull, String is_null_msg) throws Exception {
        String s = checkAndGetParam(map, param);
        if (canNull) {
            return s;
        } else {
            if (s == null) {
                String message = is_null_msg == null ? param + "is null" : is_null_msg;
                throw new Exception(message);
            }
        }
        return s;
    }

    public static String getParam(Map<String, Object> map, String param) throws Exception {
        return checkAndGetParam(map, param, true);
    }

    public static String getParamNotNull(Map<String, Object> map, String param, String is_null_msg) throws Exception {
        return checkAndGetParam(map, param, false,is_null_msg);
    }
}
