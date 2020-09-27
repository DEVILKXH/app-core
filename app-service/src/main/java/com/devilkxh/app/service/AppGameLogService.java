package com.devilkxh.app.service;


import com.baomidou.mybatisplus.service.IService;
import com.devilkxh.app.model.entity.AppGameLog;

import java.util.List;
import java.util.Map;

public interface AppGameLogService extends IService<AppGameLog> {
    List<Map<String, Object>> countGameByType(String openId);
}
