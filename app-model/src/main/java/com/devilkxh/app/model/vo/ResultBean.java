package com.devilkxh.app.model.vo;

import lombok.Data;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
@Data
public class ResultBean<T> {
    private int code;

    private String message;

    private T result;
}
