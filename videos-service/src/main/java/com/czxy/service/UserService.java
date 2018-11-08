package com.czxy.service;

import com.czxy.po.Users;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(Users user);

    Users queryUserForLogin(String username, String password);

    /**
     * 用户修改信息
     * @param user
     */
    void updateUserInfo(Users user);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    Users queryUserInfo(String userId);
}
