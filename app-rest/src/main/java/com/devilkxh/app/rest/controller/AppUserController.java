package com.devilkxh.app.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.devilkxh.app.common.helper.HttpHelper;
import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.helper.TokenHelper;
import com.devilkxh.app.common.helper.WeChatHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.model.column.AppGameLogColumn;
import com.devilkxh.app.model.column.AppUserColumn;
import com.devilkxh.app.model.constant.WechatConstant;
import com.devilkxh.app.model.entity.AppGameLog;
import com.devilkxh.app.model.entity.AppGameShareInfo;
import com.devilkxh.app.model.entity.AppUser;
import com.devilkxh.app.model.enums.ExceptionError;
import com.devilkxh.app.model.enums.WeChat;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.model.wechat.AccessToken;
import com.devilkxh.app.model.wechat.UserInfo;
import com.devilkxh.app.service.AppGameLogService;
import com.devilkxh.app.service.AppGameShareInfoService;
import com.devilkxh.app.service.AppUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
@RestController
@RequestMapping(value = "/user")
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private AppGameLogService logService;

    @Autowired
    private AppGameShareInfoService shareInfoService;

    @PostMapping(value = "/login")
    public ResultBean login(@RequestBody String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseHelper.error(ExceptionError.WECHAT_OPEN_ID_NULL);
        }
        UserInfo userInfo = WeChatHelper.getUserInfo(code);
        userService.saveOrUpdate(userInfo);
        return ResponseHelper.success(userInfo.getOpenid());
    }

    @PostMapping(value = "/logLogin")
    public ResultBean logLogin(@RequestBody String openId, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppGameLogColumn.OPEN_ID.toString(), openId).and().eq(AppGameLogColumn.TYPE.toString(), "GAME")
                .and().eq("date_format(" + AppGameLogColumn.GAME_DATE.toString() + ", '%Y-%m-%d')", sdf.format(new Date()));
        int count = logService.selectCount(wrapper);
        if(count == 0) {
            shareInfoService.saveProp(openId);
        }

        AppGameLog log = new AppGameLog();
        log.setUuid(UUID.randomUUID().toString());
        log.setGameDate(new Date());
        log.setOpenId(openId);
        log.setType("GAME");
        log.setIp(request.getRemoteHost());
        String refer = request.getHeader("Referer");
        if (com.mysql.cj.util.StringUtils.isNullOrEmpty(refer)) {
            log.setRefer(refer);
        }
        logService.insert(log);
        return ResponseHelper.success(count);
    }

    @PostMapping(value = "/sign")
    public ResultBean sign(@RequestBody String url) throws Exception {
        JSONObject res = WeChatHelper.sign(url);
        return ResponseHelper.success(res);
    }

    @PostMapping(value = "/getByOpenId")
    public ResultBean getByOpenId(@RequestBody String openId){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppUserColumn.OPEN_ID.toString(), openId);
        AppUser user = userService.selectOne(wrapper);
        return ResponseHelper.success(user);
    }

    @PostMapping(value = "/isSubscribe")
    public ResultBean isSubscribe(@RequestBody String openId){
        boolean isSubscribe = WeChatHelper.isSubcribe(openId);
        return ResponseHelper.success(isSubscribe);
    }

    @PostMapping(value = "/test")
    public String test() {
        return AppUserColumn.OPEN_ID.toString();
    }
}
