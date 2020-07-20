package com.devilkxh.app.model.enums;

import lombok.Data;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
public enum ExceptionError {
    WECHAT_OPEN_ID_NULL(10_00_0, "微信OPENID不能为空"),
    UNKOWN_EXCEPTON(99_99_9, "未知错误");

    private int code;
    private String message;

    ExceptionError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionError formatValue(int code) {
        ExceptionError []values = ExceptionError.values();
        for (ExceptionError value: values) {
            if(value.code == code) {
                return value;
            }
        }
        return UNKOWN_EXCEPTON;
    }
}
