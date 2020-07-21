package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.mapper.AppGameScoreMapper;
import com.devilkxh.app.model.entity.AppGameScore;
import com.devilkxh.app.service.AppGameScoreService;
import org.springframework.stereotype.Service;

@Service
public class AppGameScoreServiceImpl extends ServiceImpl<AppGameScoreMapper, AppGameScore> implements AppGameScoreService{
}
