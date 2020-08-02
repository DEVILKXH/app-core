package com.devilkxh.app.model.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.devilkxh.app.model.enums.UserType;
import lombok.Data;

import java.util.Date;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
@Data
@TableName("APP_USER")
public class AppUser {
    @TableId(value = "UUID")
    private String uuid;

    @TableId(value = "USER_NAME")
    private String userName;

    @TableId(value = "PASSWORD")
    private String password;

    @TableId(value = "OPEN_ID")
    private String openId;

    @TableId(value = "USER_TYPE")
    private UserType userType;

    @TableId(value = "USER_REGISTER_TIME")
    private Date userRegisterTime;

    @TableId(value = "USER_BIRTHDAY")
    private Date userBirthday;

    @TableId(value = "USER_IP")
    private String userIp;

    @TableId(value = "USER_LOGO")
    private String userLogo;

}
