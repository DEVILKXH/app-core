package com.devilkxh.app.rest.controller;


import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.model.column.AppGameShareInfoColumn;
import com.devilkxh.app.model.entity.AppGameShareInfo;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.service.AppGameShareInfoService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/share/info")
public class AppGameShareInfoController {
    @Autowired
    public AppGameShareInfoService shareInfoService;

    @PostMapping(value = "/save")
    public ResultBean save(@RequestBody AppGameShareInfo shareInfo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        QueryWrapper wrapper2 = new QueryWrapper();
        wrapper2.eq(AppGameShareInfoColumn.SHARE_OPEN_ID.toString(), shareInfo.getShareOpenId()).
                and().eq(AppGameShareInfoColumn.SHARE_DATE.toString(), sdf.format(new Date()));

        int count = shareInfoService.selectCount(wrapper2);
        if (count >= 5) {
            return ResponseHelper.success("您今天助力次数达到上限，不能在助力了哦");
        }

        QueryWrapper wrapper3 = new QueryWrapper();
        wrapper3.eq(AppGameShareInfoColumn.OPEN_ID.toString(), shareInfo.getOpenId()).
                and().eq(AppGameShareInfoColumn.SHARE_DATE.toString(), sdf.format(new Date()));

        count = shareInfoService.selectCount(wrapper3);
        if (count >= 3) {
            return ResponseHelper.success("对方今天助力次数达到上限，不能在助力了哦");
        }

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppGameShareInfoColumn.OPEN_ID.toString(), shareInfo.getOpenId()).
                and().eq(AppGameShareInfoColumn.SHARE_OPEN_ID.toString(), shareInfo.getShareOpenId()).
                and().eq(AppGameShareInfoColumn.SHARE_TYPE.toString(), shareInfo.getShareType().toUpperCase()).
                and().eq(AppGameShareInfoColumn.SHARE_DATE.toString(), sdf.format(new Date()));

        count = shareInfoService.selectCount(wrapper);
        if (count != 0) {
            return ResponseHelper.success("您今天已经助力过了，不能在助力了哦");
        }




        shareInfo.setShareDate(sdf.format(new Date()));
        shareInfo.setUuid(UUID.randomUUID().toString());
        shareInfo.setShareType(shareInfo.getShareType().toUpperCase());
        shareInfoService.insert(shareInfo);
        return ResponseHelper.success("助力成功");
    }


    @PostMapping(value = "/getShareInfo")
    public ResultBean getShareInfo(@RequestBody AppGameShareInfo shareInfo) {
        if(StringUtils.isNullOrEmpty(shareInfo.getShareType())) {
            shareInfo.setShareType("GAME");
        }
        List<Map<String, String>> list = shareInfoService.getShareInfo(shareInfo);
        return ResponseHelper.success(list);
    }
}
