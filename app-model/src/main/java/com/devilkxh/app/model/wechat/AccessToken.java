package com.devilkxh.app.model.wechat;

import lombok.Data;

@Data
public class AccessToken {
    private String token;
    // 凭证有效时间，单位：秒
    private int expiresIn;
}
