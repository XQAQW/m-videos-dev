package com.czxy.service;

import com.czxy.po.Users;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 保存用户
     * @param user
     */
    public void saveUser(Users user);

    Users queryUserForLogin(String username, String password);
}
