package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.mapper.AppGamePointMapper;
import com.devilkxh.app.model.column.AppGamePointColumn;
import com.devilkxh.app.model.entity.AppGamePoint;
import com.devilkxh.app.service.AppGamePointService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author kexiaohong
 * @since 2020/7/24
 */
@Service
public class AppGamePointServiceImpl extends ServiceImpl<AppGamePointMapper, AppGamePoint> implements AppGamePointService{
    @Override
    public void nextPoint(AppGamePoint point) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppGamePointColumn.OPEN_ID.toString(), point.getOpenId()).eq(AppGamePointColumn.POINT.toString(), point.getPoint());
        List<AppGamePoint> currentPoints = baseMapper.selectList(wrapper);
        if (null == currentPoints || currentPoints.isEmpty()) {
            point.setUuid(UUID.randomUUID().toString());
            baseMapper.insert(point);
        } else {
            AppGamePoint currentPoint = currentPoints.get(0);
            if (currentPoint.getScore() < point.getScore()) {
                currentPoint.setScore(point.getScore());
                baseMapper.updateById(currentPoint);
            }
        }
    }
}
