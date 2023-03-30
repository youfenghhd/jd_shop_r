package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    //@Select("select * from user")
    //public List<User> findAll();
    //@Select("select * from user where id = #{id}")
    //public User findById(Integer integer);

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
