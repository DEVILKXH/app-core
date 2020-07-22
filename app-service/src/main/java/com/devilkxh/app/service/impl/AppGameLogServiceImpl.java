package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.mapper.AppGameLogMapper;
import com.devilkxh.app.model.entity.AppGameLog;
import com.devilkxh.app.service.AppGameLogService;
import org.springframework.stereotype.Service;

@Service
public class AppGameLogServiceImpl extends ServiceImpl<AppGameLogMapper, AppGameLog> implements AppGameLogService {
}
