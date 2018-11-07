package com.czxy.controller;

import com.czxy.po.Users;
import com.czxy.service.UserService;
import com.czxy.utils.JSONResult;
import com.czxy.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户注册登录的接口",tags = {"注册和登录的controller"})
public class RegistLoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value ="用户注册",notes = "用户注册的接口")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody Users user) {
        try {
            //1判断用户名和密码必须不为空
            if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
                return JSONResult.errorException("用户名和密码不能为空");
            }
            //2判断用户名是否存在
            boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
            //3保存用户，注册信息
            //如果username不存在
            if (!usernameIsExist) {
                //默认把登录的username设置为他的昵称，后期可以自己修改
                user.setNickname(user.getUsername());
                //把用户密码进行md5加密
                user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
                //初始化粉丝数量为0
                user.setFansCounts(0);
                //初始化关注的数量为0
                user.setReceiveLikeCounts(0);
                //初始化获赞的数量为0
                user.setFollowCounts(0);
                //保存当前用户
                userService.saveUser(user);
            } else {
                return JSONResult.errorMsg("用户名已存在，请换一个试试");
            }
            return JSONResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("系统出错，请重新尝试，或者联系我们的维护人员:xqwQAQwq@163.com");
        }
    }

}
