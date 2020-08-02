package com.devilkxh.app.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.devilkxh.app.common.helper.HttpHelper;
import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.helper.TokenHelper;
import com.devilkxh.app.common.helper.WeChatHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.model.column.AppUserColumn;
import com.devilkxh.app.model.constant.WechatConstant;
import com.devilkxh.app.model.entity.AppUser;
import com.devilkxh.app.model.enums.ExceptionError;
import com.devilkxh.app.model.enums.WeChat;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.model.wechat.AccessToken;
import com.devilkxh.app.model.wechat.UserInfo;
import com.devilkxh.app.service.AppUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/login")
    public ResultBean login(@RequestBody String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseHelper.error(ExceptionError.WECHAT_OPEN_ID_NULL);
        }
        UserInfo userInfo = WeChatHelper.getUserInfo(code);
        userService.saveOrUpdate(userInfo);
        return ResponseHelper.success(userInfo.getOpenid());
    }

    @PostMapping(value = "/sign")
    public ResultBean sign() {
//        AccessToken accessToken = WeChatHelper.getAccessToken();
        return ResponseHelper.success();
    }

    @PostMapping(value = "/test")
    public String test() {
        return AppUserColumn.OPEN_ID.toString();
    }
}
