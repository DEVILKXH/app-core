package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.mapper.AppGamePointMapper;
import com.devilkxh.app.mapper.AppGameScoreMapper;
import com.devilkxh.app.model.column.AppGameScoreColumn;
import com.devilkxh.app.model.entity.AppGamePoint;
import com.devilkxh.app.model.entity.AppGameScore;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.service.AppGamePointService;
import com.devilkxh.app.service.AppGameScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AppGameScoreServiceImpl extends ServiceImpl<AppGameScoreMapper, AppGameScore> implements AppGameScoreService{

    @Autowired
    private AppGamePointService pointService;

    @Override
    public List<Map<String, String>> getRankList() {
        return baseMapper.getRankList();
    }

    @Override
    public ResultBean save(AppGameScore score) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        score.setUuid(UUID.randomUUID().toString());
        score.setDate(sdf.format(new Date()));
        baseMapper.insert(score);
        AppGamePoint point = new AppGamePoint();
        point.setOpenId(score.getOpenId());
        point.setScore(score.getScore());
        point.setPoint(score.getPoint());
        pointService.nextPoint(point);
        return ResponseHelper.success();
    }
}
