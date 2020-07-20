package com.devilkxh.app.service.impl;

import com.devilkxh.app.model.entity.DemoModel;
import com.devilkxh.app.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author kexiaohong
 * @since 2020/7/17
 */
@Service
public class DemoServiceImpl implements DemoService{

    @Override
    public DemoModel selectOne() {
        DemoModel model = new DemoModel();
        model.setUserName("肖丽虹");
        model.setBirthday(new Date());
        return model;
    }
}
