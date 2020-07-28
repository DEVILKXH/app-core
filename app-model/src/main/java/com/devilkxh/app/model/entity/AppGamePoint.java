package com.devilkxh.app.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author kexiaohong
 * @since 2020/7/24
 */
@Data
@TableName(value = "APP_GAME_POINT")
public class AppGamePoint {

    @TableId(value = "UUID")
    private String uuid;

    @TableField(value = "OPEN_ID")
    private String openId;

    @TableField(value = "POINT")
    private int point;

    @TableField(value = "SCORE")
    private int score;
}
