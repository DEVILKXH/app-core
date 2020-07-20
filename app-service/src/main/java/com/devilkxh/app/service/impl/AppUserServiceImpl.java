package com.devilkxh.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.devilkxh.app.mapper.AppUserMapper;
import com.devilkxh.app.model.entity.AppUser;
import com.devilkxh.app.service.AppUserService;
import org.springframework.stereotype.Service;

/**
 * @author kexiaohong
 * @since 2020/7/18
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService{
}
