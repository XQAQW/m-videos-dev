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

import java.util.UUID;

@RestController
@Api(value = "用户注册登录的接口",tags = {"注册和登录的controller"})
public class RegistLoginController extends BasicController{

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
                //如果当前用户名已存在返回信息
                return JSONResult.errorMsg("用户名已存在，请换一个试试");
            }
            //注册成功保存用户信息
            user.setPassword("");
            //UUID随机生成
            String s = UUID.randomUUID().toString();

            redis.set(USER_REDIS_SESSION + ":" + user.getId(), s, 1000 * 60 * 30);

            return JSONResult.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("系统出错，请重新尝试，或者联系我们的维护人员:xqwQAQwq@163.com");
        }
    }

    @ApiOperation(value = "用户登录", notes = "用户登录的接口")
    @PostMapping("/login")
    public JSONResult login(@RequestBody Users users) {
        try {
            //获取用户名
            String username = users.getUsername();
            //获取加密前密码
            String password = users.getPassword();

            // 判断用户是否存在(并将密码进行md5加密用于比较)
            Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

            //返回
            if (userResult != null) {
                //设置用户密码为""
                userResult.setPassword("");
                //返回
                return JSONResult.ok(userResult);
            } else {
                //没有查询到则说明账户或密码不正确
                return JSONResult.errorMsg("用户名或密码不正确，请重试...");
            }
        } catch (Exception e) {
            //服务器出错
            e.printStackTrace();
            return JSONResult.errorMsg("系统出现错误请重新尝试，或联系通过邮箱xqwQAQwq@163.com联系我们");
        }
    }

}
