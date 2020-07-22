package com.devilkxh.app.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "APP_GAME_LOG")
public class AppGameLog {
    @TableId(value = "UUID")
    private String uuid;

    @TableField(value = "GAME_DATE")
    private Date gameDate;

    @TableField(value = "REFER")
    private String refer;

    @TableField(value = "TYPE")
    private String type;

    @TableField(value = "OPEN_ID")
    private String openId;

    @TableField(value = "IP")
    private String ip;
}
