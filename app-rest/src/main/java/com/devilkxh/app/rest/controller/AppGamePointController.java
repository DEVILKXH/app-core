package com.devilkxh.app.rest.controller;

import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.model.column.AppGamePointColumn;
import com.devilkxh.app.model.entity.AppGamePoint;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.service.AppGamePointService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author kexiaohong
 * @since 2020/7/24
 */
@RestController
@RequestMapping(value = "/point")
public class AppGamePointController {
    @Autowired
    private AppGamePointService pointService;

    @PostMapping(value = "/getCurrentPoint")
    public ResultBean getCurrentPoint(@RequestBody AppGamePoint point) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppGamePointColumn.OPEN_ID.toString(), point.getOpenId()).orderBy(AppGamePointColumn.POINT.toString(), false);
        List<AppGamePoint> points = pointService.selectList(wrapper);
        if (null == points || points.isEmpty()) {
            AppGamePoint newPoint = new AppGamePoint();
            newPoint.setOpenId(point.getOpenId());
            newPoint.setUuid(UUID.randomUUID().toString());
            pointService.insert(newPoint);
            return ResponseHelper.success(0);
        }
        return ResponseHelper.success(points.get(0).getPoint());
    }

    @PostMapping(value = "/nextPoint")
    public void nextPoint(@RequestBody AppGamePoint point) {
        pointService.nextPoint(point);
    }

    @PostMapping(value = "/updateScore")
    public void updateScore(@RequestBody AppGamePoint point) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppGamePointColumn.OPEN_ID.toString(), point.getOpenId()).eq(AppGamePointColumn.POINT.toString(), point.getPoint());
        List<AppGamePoint> points = pointService.selectList(wrapper);
        if (null != points && !points.isEmpty()) {
            AppGamePoint gamePoint = points.get(0);
            gamePoint.setScore(point.getScore());
            pointService.updateById(gamePoint);
        }
    }
}
