package com.devilkxh.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.devilkxh.app.model.entity.AppUser;
import com.devilkxh.app.model.wechat.UserInfo;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
public interface AppUserService extends IService<AppUser> {
    void saveOrUpdate(UserInfo userInfo);
}
