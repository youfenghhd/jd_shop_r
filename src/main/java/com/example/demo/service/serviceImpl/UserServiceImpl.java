package com.example.demo.service.serviceImpl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User person) {
        return userMapper.save(person);
    }

    @Override
    public int update(User person) {
        return userMapper.update(person);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findUacc(String uacc, String upwd) {
        return userMapper.findUacc(uacc, upwd);
    }

    @Override
    public List<User> findUname(String uname) {
        return userMapper.findUname(uname);
    }

    @Override
    public int updateUser(int uid, String upwd) {
        return userMapper.updateUser(uid, upwd);
    }

    @Override
    public int changeUtype(int uid, String utype) {
        return userMapper.changeUtype(uid, utype);
    }

}
