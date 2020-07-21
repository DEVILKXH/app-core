package com.devilkxh.app.rest.controller;

import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.model.column.AppGameScoreColumn;
import com.devilkxh.app.model.column.AppUserColumn;
import com.devilkxh.app.model.entity.AppGameScore;
import com.devilkxh.app.model.enums.ExceptionError;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.service.AppGameScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/score")
public class AppGameScoreController {

    @Autowired
    private AppGameScoreService gameScoreService;

    @PostMapping(value = "/save")
    public ResultBean save(@RequestBody AppGameScore score) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        score.setUuid(UUID.randomUUID().toString());
        score.setDate(sdf.format(new Date()));
        gameScoreService.insert(score);
        return ResponseHelper.success();
    }

    @PostMapping(value = "/count")
    public ResultBean count(@RequestBody AppGameScore score) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppGameScoreColumn.OPEN_ID.toString(), score.getOpenId()).and().eq(AppGameScoreColumn.GAME_DATE.toString() , sdf.format(new Date()));
        int count  = gameScoreService.selectCount(wrapper);
        return ResponseHelper.success(count);
    }
}
