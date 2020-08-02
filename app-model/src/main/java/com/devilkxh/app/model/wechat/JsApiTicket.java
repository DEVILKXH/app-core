package com.devilkxh.app.model.wechat;

import lombok.Data;

@Data
public class JsApiTicket {
    private String errcode;

    private String errmsg;

    private String ticket;

    private int expires_in;
}
