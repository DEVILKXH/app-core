package com.devilkxh.app.rest.controller;

import com.devilkxh.app.model.entity.DemoModel;
import com.devilkxh.app.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kexiaohong
 * @since 2020/7/17
 */
@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @Autowired
    private DemoService service;

    @PostMapping(value = "selectOne")
    public DemoModel selectOne() {
        return service.selectOne();
    }
}
