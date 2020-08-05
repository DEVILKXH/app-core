package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.common.wrapper.QueryWrapper;
import com.devilkxh.app.mapper.AppUserMapper;
import com.devilkxh.app.model.column.AppUserColumn;
import com.devilkxh.app.model.entity.AppUser;
import com.devilkxh.app.model.wechat.UserInfo;
import com.devilkxh.app.service.AppUserService;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService{
    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        AppUser query = new AppUser();
        query.setOpenId(userInfo.getOpenid());
        AppUser user = baseMapper.selectOne(query);
        if (null != user && !StringUtils.isNullOrEmpty(user.getOpenId())) {
            user.setOpenId(userInfo.getOpenid());
            user.setUserName(userInfo.getNickname());
            user.setUserLogo(userInfo.getHeadimgurl());
            baseMapper.updateById(user);
        } else {
            AppUser appUser = new AppUser();
            appUser.setUuid(UUID.randomUUID().toString());
            appUser.setOpenId(userInfo.getOpenid());
            appUser.setUserName(userInfo.getNickname());
            appUser.setUserLogo(userInfo.getHeadimgurl());
            appUser.setUserRegisterTime(new Date());
            baseMapper.insert(user);
        }
    }
}
