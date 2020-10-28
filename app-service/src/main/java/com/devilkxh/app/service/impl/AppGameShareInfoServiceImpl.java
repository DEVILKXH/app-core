package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.mapper.AppGameShareInfoMapper;
import com.devilkxh.app.model.entity.AppGameShareInfo;
import com.devilkxh.app.service.AppGameShareInfoService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AppGameShareInfoServiceImpl extends ServiceImpl<AppGameShareInfoMapper, AppGameShareInfo> implements AppGameShareInfoService {
    @Override
    public List<Map<String, String>> getShareInfo(AppGameShareInfo shareInfo) {
        return baseMapper.getShareInfo(shareInfo);
    }

    @Override
    public List<Map<String, Object>> countGameByType(String openId) {
        return baseMapper.countGameByType(openId);
    }

    @Override
    public void saveProp(String openid) {
        String [] type = {"HELP", "BOOM", "REFRESH",  "FROZEN"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 0; i < type.length; i ++) {
            AppGameShareInfo shareInfo = new AppGameShareInfo();
            shareInfo.setUuid(UUID.randomUUID().toString());
            shareInfo.setShareType(type[i]);
            shareInfo.setOpenId(openid);
            shareInfo.setShareDate(sdf.format(new Date()));
            shareInfo.setShareOpenId("SYSTEM");
            baseMapper.insert(shareInfo);
        }

    }
}
