package com.devilkxh.app.rest.controller;

import com.devilkxh.app.model.entity.AppGameLog;
import com.devilkxh.app.model.vo.ResultBean;
import com.devilkxh.app.service.AppGameLogService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/log")
public class AppGameLogController {

    @Autowired
    private AppGameLogService logService;

    @PostMapping(value = "/save")
    public void saveLog(@RequestBody AppGameLog log, HttpServletRequest request) {
        log.setUuid(UUID.randomUUID().toString());
        log.setGameDate(new Date());
        log.setIp(request.getRemoteHost());
        String refer = request.getHeader("Referer");
        if (StringUtils.isNullOrEmpty(refer)) {
            log.setRefer(refer);
        }
        logService.insert(log);
    }
}
