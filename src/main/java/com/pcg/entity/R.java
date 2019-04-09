package com.pcg.entity;

import lombok.Data;

@Data
public class R {

    public static final int CODE_SUCCESS = 1;
    public static final int CODE_FAIL = 0;
    public static final int CODE_NOT_LOGIN = -1;
    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_FAIL = "操作失败";
    public static final String MSG_NOT_LOGIN = "没有登录";
    int code;
    String msg;
    String errorMsg;
    Object content;

    public R () {

    }

    public R ( Boolean b) {
        if (!b) {
            this.code = CODE_FAIL;
            this.msg = MSG_FAIL;
        } else {
            this.code = CODE_SUCCESS;
            this.msg = MSG_SUCCESS;
        }
    }

    public R ( Object content) {
        super();
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
        this.content = content;
    }

    public R ( Boolean b, Object content) {
        if (!b) {
            this.code = CODE_FAIL;
            this.msg = MSG_FAIL;
        } else {
            this.code = CODE_SUCCESS;
            this.msg = MSG_SUCCESS;
        }
        this.content = content;
    }

    public R ( int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public R ( int code, String msg, String errorMsg) {
        super();
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }

    public R ( int code, String msg, Object content) {
        super();
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public R ( int code, String msg, String errorMsg, Object content) {
        super();
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
        this.content = content;
    }

}
