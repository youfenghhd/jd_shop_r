package com.example.demo.service;

import com.example.demo.entity.Cart;

import java.util.List;

public interface CartService {
    //获取购物车信息
    List<Cart> getAll();
    //添加订单信息
    int addInfo(Cart cart);
    //删除订单
    int deleteInfo(int caid);
}
