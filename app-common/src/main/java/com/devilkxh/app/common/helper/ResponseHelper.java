package com.devilkxh.app.common.helper;

import com.devilkxh.app.model.enums.ExceptionError;
import com.devilkxh.app.model.vo.ResultBean;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
public class ResponseHelper {

    public static ResultBean success() {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(200);
        return resultBean;
    }

    public static <T> ResultBean success(T t) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(200);
        resultBean.setResult(t);
        return resultBean;
    }

    public static ResultBean error(ExceptionError exception) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(exception.getCode());
        resultBean.setMessage(exception.getMessage());
        return resultBean;
    }

    public static ResultBean error(int code, String message) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }

}
