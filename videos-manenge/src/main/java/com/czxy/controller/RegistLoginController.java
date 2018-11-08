package com.czxy.controller;

import com.czxy.po.Users;
import com.czxy.po.vo.UsersVO;
import com.czxy.service.UserService;
import com.czxy.utils.JSONResult;
import com.czxy.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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

    /**
     * 注册功能
     * @param user
     * @return
     */
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
            //将用户数据存入设置一个唯一标识与其他相对应
            UsersVO usersVO = setUserRedisSessionToken(user);
            return JSONResult.ok(usersVO);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("系统出错，请重新尝试，或者联系我们的维护人员:xqwQAQwq@163.com");
        }
    }

    /**
     * 将前台传送过来的值存放到redis中形成无状态会话
     * @param userRedisSessionToken
     * @return
     */
    public UsersVO setUserRedisSessionToken(Users userRedisSessionToken) {
        //获取uuid随机码
        String uniqueToken = UUID.randomUUID().toString();
        //存放到redis中 用:是进行分类层级的用处
        redis.set(USER_REDIS_SESSION + ":" + userRedisSessionToken.getId(), uniqueToken, 1000 * 60 * 30);
        //创建user对象
        UsersVO usersaVO = new UsersVO();
        BeanUtils.copyProperties(userRedisSessionToken, usersaVO);
        //将uuid值设置到vo中
        usersaVO.setUserToken(uniqueToken);
        return usersaVO;
    }

    /**
     * 登录功能
     * @param users
     * @return
     */
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
                //登录成功，将数据保存到redis中
                UsersVO usersVO = setUserRedisSessionToken(userResult);
                //返回
                return JSONResult.ok(usersVO);
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

    @PostMapping("/logout/query")
    @ApiOperation(value = "用户注销",notes = "用户注销的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    public JSONResult login(String userId,String id) {
        try {
            redis.del(USER_REDIS_SESSION + ":" + userId);
            return JSONResult.ok("注销成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("系统出现错误，请通过邮箱xqwQAQwq@163.com联系我们");
        }
    }
}
