package com.devilkxh.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.devilkxh.app.model.entity.AppGamePoint;

/**
 * @author: kexiaohong
 * @date: 2020/7/24
 */
public interface AppGamePointService extends IService<AppGamePoint>{
    void nextPoint(AppGamePoint point);
}
