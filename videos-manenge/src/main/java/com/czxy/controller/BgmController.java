package com.czxy.controller;

import com.czxy.service.BgmService;
import com.czxy.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bgm")
@Api(value = "背景音乐业务的接口",tags = {"背景音乐业务的Controller"})
public class BgmController {

    @Autowired
    private BgmService bgmService ;

    @ApiOperation(value = "获取背景音乐列表",notes = "获取背景音乐列表的接口")
    @PostMapping("/list")
    public JSONResult list() {
        try {
            return JSONResult.ok(bgmService.queryBgmList());
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("系统出现错误,请通过邮箱xqwQAQwq@163.com联系我们...");
        }
    }
}
