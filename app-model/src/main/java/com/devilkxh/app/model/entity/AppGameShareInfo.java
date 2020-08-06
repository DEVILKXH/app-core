package com.devilkxh.app.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName(value = "APP_GAME_SHARE_INFO")
public class AppGameShareInfo {
    @TableId(value = "UUID")
    private String uuid;

    @TableField("OPEN_ID")
    private String openId;

    @TableField("SHARE_DATE")
    private String shareDate;

    @TableField("DESC")
    private String desc;

    @TableField("SHARE_OPEN_ID")
    private String shareOpenId;
}
