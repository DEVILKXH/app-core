package com.devilkxh.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.devilkxh.app.model.entity.AppGameScore;
import com.devilkxh.app.model.vo.ResultBean;

import java.util.List;
import java.util.Map;

public interface AppGameScoreService extends IService<AppGameScore> {
    List<Map<String, String>> getRankList();

    ResultBean save(AppGameScore score);
}
