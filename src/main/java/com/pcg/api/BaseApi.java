package com.pcg.api;

import com.pcg.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

@Slf4j
public class BaseApi {

    DecimalFormat df = new DecimalFormat("#.000");
    DecimalFormat df2 = new DecimalFormat("#.00");
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    protected SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public
    PlatformTransactionManager transactionManager;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public R fail( String errorMsg) {
        return new R(R.CODE_FAIL, R.MSG_FAIL, errorMsg);
    }

    public R fail(String msg, Object object) {
        return new R(R.CODE_FAIL, R.MSG_FAIL, msg, object);
    }

    public R ok(Object obj) {
        return new R(obj);
    }

    public R ok() {
        return ok("ok");
    }

}
