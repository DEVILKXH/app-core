package com.devilkxh.app.rest.controller;

import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.helper.WeChatHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.model.Constant;
import com.devilkxh.app.model.column.AppGameLogColumn;
import com.devilkxh.app.model.column.AppGameScoreColumn;
import com.devilkxh.app.model.column.AppGameShareInfoColumn;
import com.devilkxh.app.model.column.AppUserColumn;
import com.devilkxh.app.model.entity.AppGameLog;
import com.devilkxh.app.model.entity.AppGameScore;
import com.devilkxh.app.model.entity.AppGameShareInfo;
import com.devilkxh.app.model.enums.ExceptionError;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.service.AppGameLogService;
import com.devilkxh.app.service.AppGamePointService;
import com.devilkxh.app.service.AppGameScoreService;
import com.devilkxh.app.service.AppGameShareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/score")
public class AppGameScoreController {

    @Autowired
    private AppGameScoreService scoreService;

    @Autowired
    private AppGameShareInfoService shareInfoService;

    @Autowired
    private AppGamePointService pointService;

    @Autowired
    private AppGameLogService logService;

    @PostMapping(value = "/save")
    public ResultBean save(@RequestBody AppGameScore score) {
        return scoreService.save(score);
    }

    @PostMapping(value = "/count")
    public ResultBean count(@RequestBody AppGameScore score) {
//        if(!WeChatHelper.isSubcribe(score.getOpenId())) {
//            return ResponseHelper.error(ExceptionError.WECHAT_SUBSCRIBE_ERROR);
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppGameLogColumn.OPEN_ID.toString(), score.getOpenId()).and().eq(AppGameLogColumn.TYPE.toString() , Constant.LOG_TYPE).and().eq("date_format(GAME_DATE, '%Y-%m-%d')" , date);
        int count  = logService.selectCount(wrapper);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(AppGameScoreColumn.OPEN_ID.toString(), score.getOpenId()).and().eq(AppGameShareInfoColumn.SHARE_DATE.toString() , date).and().eq(AppGameShareInfoColumn.SHARE_TYPE.toString(), "GAME");
        int count2  = shareInfoService.selectCount(queryWrapper);
        return ResponseHelper.success(count - count2);
    }

    @PostMapping(value = "/rank")
    public ResultBean rank() {
        return ResponseHelper.success(scoreService.getRankList());
    }

    @PostMapping(value = "/groupCount")
    public ResultBean groupCount(@RequestBody AppGameScore score) {
        List<Map<String, Object>> logCount = logService.countGameByType(score.getOpenId());
        List<Map<String, Object>> shareCount = shareInfoService.countGameByType(score.getOpenId());
        Map<String, Integer> result = new HashMap<>();
        for(Map<String, Object> map: shareCount) {
            result.put(String.valueOf(map.get("type")), Integer.valueOf(map.get("count").toString()));
        }
        for(Map<String, Object> map: logCount) {
            String key = String.valueOf(map.get("type"));
            if(!result.containsKey(key)) {
                continue;
            }
            int value = result.get(key) - Integer.valueOf(map.get("count").toString());
            result.put(key, value);
        }
        return ResponseHelper.success(result);
    }

    @PostMapping(value = "/getMaxScore")
    public ResultBean getMaxScore(@RequestBody AppGameScore score) {
        int maxScore = scoreService.getMaxScore(score);
        int totalScore = scoreService.getAllScore(score);
        Map<String, Integer> map = new HashMap<>();
        map.put("max", maxScore);
        map.put("total", totalScore);
        return ResponseHelper.success(map);
    }
}
