package com.devilkxh.app.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "APP_GAME_SCORE")
public class AppGameScore {

    @TableId(value = "UUID")
    private String uuid;

    @TableField(value = "OPEN_ID")
    private String openId;

    @TableField(value = "GAME_DATE")
    private String date;

    @TableField(value = "SCORE")
    private String score;
}
