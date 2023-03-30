package com.example.demo.service;


import com.example.demo.entity.User;

import java.util.List;



import java.util.List;

public interface UserService {
    // 添加用户
    int save(User person);
    // 修改用户
    int update(User person);
    // 删除用户
    int delete(Integer id);
    // 查询所有用户
    List<User> findAll();
    // 根据ID查询用户
    User findById(Integer id);
    //根据账号密码查询用户方法
    User findUacc(String uacc, String upwd);
    //根据用户名查找
    List<User> findUname(String uname);
    //重置密码
    int updateUser(int uid, String upwd);
    //修改状态
    int changeUtype(int uid, String utype);

}
