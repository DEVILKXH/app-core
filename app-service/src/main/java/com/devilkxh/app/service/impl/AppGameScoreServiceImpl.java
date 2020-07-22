package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.mapper.AppGameScoreMapper;
import com.devilkxh.app.model.entity.AppGameScore;
import com.devilkxh.app.service.AppGameScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppGameScoreServiceImpl extends ServiceImpl<AppGameScoreMapper, AppGameScore> implements AppGameScoreService{
    @Override
    public List<Map<String, String>> getRankList() {
        return baseMapper.getRankList();
    }
}
