package com.devilkxh.app.rest.controller;

import com.devilkxh.app.common.helper.ResponseHelper;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.model.column.AppUserColumn;
import com.devilkxh.app.model.entity.AppUser;
import com.devilkxh.app.model.enums.ExceptionError;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.service.AppUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultBean login(@RequestBody AppUser user) {
        if (StringUtils.isBlank(user.getOpenId())) {
            return ResponseHelper.error(ExceptionError.WECHAT_OPEN_ID_NULL);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(AppUserColumn.OPEN_ID.toString(), user.getOpenId());

        return ResponseHelper.success();
    }

    @PostMapping(value = "/test")
    public String test() {
        return AppUserColumn.OPEN_ID.toString();
    }
}
