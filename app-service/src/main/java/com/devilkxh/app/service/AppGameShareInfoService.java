package com.devilkxh.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.devilkxh.app.model.entity.AppGameShareInfo;

import java.util.List;
import java.util.Map;

public interface AppGameShareInfoService extends IService<AppGameShareInfo> {
    List<Map<String, String>> getShareInfo(AppGameShareInfo openId);
    List<Map<String, Object>> countGameByType(String openId);

    void saveProp(String openid);
}
