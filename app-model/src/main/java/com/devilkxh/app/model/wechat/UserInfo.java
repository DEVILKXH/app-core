package com.devilkxh.app.model.wechat;

import lombok.Data;

@Data
public class UserInfo {
    private String errcode;

    private String errmsg;

    private String openid;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String privilege;

    private String unionid;
}
