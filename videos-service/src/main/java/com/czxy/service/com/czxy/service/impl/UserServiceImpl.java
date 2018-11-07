package com.czxy.service.com.czxy.service.impl;

import com.czxy.mapper.UsersMapper;
import com.czxy.po.Users;
import com.czxy.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users users = new Users();
        users.setUsername(username);
        Users result = usersMapper.selectOne(users);
        return result == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(Users user) {
        String s = sid.nextShort();
        user.setId(s);
        usersMapper.insert(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        //准备example条件查询
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        //设置条件
        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);
        //返回查询结果
        return usersMapper.selectOneByExample(example);
    }
}
