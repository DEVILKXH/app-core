package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.mapper.AppGameShareInfoMapper;
import com.devilkxh.app.model.entity.AppGameShareInfo;
import com.devilkxh.app.service.AppGameShareInfoService;
import org.springframework.stereotype.Service;

@Service
public class AppGameShareInfoServiceImpl extends ServiceImpl<AppGameShareInfoMapper, AppGameShareInfo> implements AppGameShareInfoService {
}
