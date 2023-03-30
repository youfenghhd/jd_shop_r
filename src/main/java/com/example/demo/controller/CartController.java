package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("购物车")
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/getCartInfo")
    @ApiOperation(value = "购物车信息",notes = "查询购物车")
    public ResultUtils<?> getAll(){
        List<Cart> all = cartService.getAll();
        if (all.isEmpty()){
            return ResultUtils.failed();
        }else {
            return ResultUtils.success(all);
        }
    }

    @PostMapping("/addCartInfo")
    @ApiOperation(value = "添加购物车",notes = "添加购物车")
    public ResultUtils<?> addCartInfo(@RequestBody Cart cart){
        int i = cartService.addInfo(cart);
        if (i>0){
            return ResultUtils.success(cart);
        }else {
            return ResultUtils.failed();
        }
    }
    @DeleteMapping("/deleteCartInfo/{caid}")
    @ApiOperation(value = "删除购物车",notes = "删除购物车")
    public ResultUtils<?> deleteInfo(@PathVariable int caid){
        int i = cartService.deleteInfo(caid);
        if (i>0){
            return ResultUtils.success(caid);
        }else {
            return ResultUtils.failed();
        }
    }
}
