package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.mapper.AppGameShareInfoMapper;
import com.devilkxh.app.model.entity.AppGameShareInfo;
import com.devilkxh.app.service.AppGameShareInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppGameShareInfoServiceImpl extends ServiceImpl<AppGameShareInfoMapper, AppGameShareInfo> implements AppGameShareInfoService {
    @Override
    public List<Map<String, String>> getShareInfo(String openId) {
        return baseMapper.getShareInfo(openId);
    }
}
