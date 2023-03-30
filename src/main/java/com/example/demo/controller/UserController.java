package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//跨域注解
@CrossOrigin
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/findById/{uid}")
    @ApiOperation(value = "根据ID查询用户", notes = "查询用户")
    public ResultUtils<?> findById(@PathVariable Integer uid) {
        User user = userService.findById(uid);
        if (user == null) {
            return ResultUtils.failed();
        } else {
            return ResultUtils.success(user);
        }
    }

    // @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有用户", notes = "查询用户")
    public ResultUtils<?> findAll() {
        List<User> userList = userService.findAll();
        if (!userList.isEmpty()) {
            return ResultUtils.success(userList);
        } else {
            return ResultUtils.failed();
        }
        //return userService.findAll();
    }

    //插入
    @PostMapping(value = "/save")
    @ApiOperation(value = "插入用户", notes = "插入用户")
    public ResultUtils<?> save(@RequestBody User user) {
        System.out.println("save:" + user);
        int save = userService.save(user);
        if (save > 0) {
            return ResultUtils.success(user);
        } else {
            return ResultUtils.failed();
        }

    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "更新用户", notes = "更新用户")
    public ResultUtils<?> update(@RequestBody User user) {
        int update = userService.update(user);
        if (update > 0) {
            return ResultUtils.success(user);
        } else {
            return ResultUtils.failed();
        }
    }

    // @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/delete/{uid}")
    @ApiOperation(value = "按id删除用户", notes = "删除用户")
    public ResultUtils<?> delete(@PathVariable Integer uid) {
        int delete = userService.delete(uid);
        if (delete > 0) {
            return ResultUtils.success(uid);
        } else {
            return ResultUtils.failed();
        }

    }

    //根据账号密码查询用户
    @PostMapping(value = "findUacc")
    @ApiOperation(value = "根据账号密码查询用户", notes = "按ID查询用户")
    public ResultUtils<?> findUser(@RequestBody User user) {
        User bean = userService.findUacc(user.getUacc(), user.getUpwd());
        if (bean == null) {
            return ResultUtils.failed();
        } else {
            return ResultUtils.success(bean);
        }
    }

    //根据用户名模糊查找
    @PostMapping(value = "findUname")
    @ApiOperation(value = "根据用户名模糊查找", notes = "查询用户")
    public ResultUtils<?> findUname(@RequestBody User user) {
        List<User> userList = userService.findUname(user.getUname());
        if (!userList.isEmpty()) {
            return ResultUtils.success(userList);
        } else {
            return ResultUtils.failed();
        }
    }

    //重置密码
    @PostMapping(value = "/updateUser")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public ResultUtils<?> updateUser(@RequestBody User user) {
        int judge = userService.updateUser(user.getUid(), "0000");
        if (judge > 0) {
            return ResultUtils.success(user.getUid());
        } else {
            return ResultUtils.failed();
        }
    }

    //修改状态
    @PostMapping(value = "/changeUtype")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    public ResultUtils<?> changeUtype(@RequestBody User user) {
        String utype = user.getUtype();
        if (utype.equals("启用")) {
            utype = "禁用";
        } else {
            utype = "启用";
        }
        int judge = userService.changeUtype(user.getUid(), utype);
        if (judge > 0) {
            return ResultUtils.success(user.getUtype());
        } else {
            return ResultUtils.failed();
        }
    }
}
