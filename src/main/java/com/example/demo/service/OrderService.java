package com.example.demo.service;

import com.example.demo.entity.Orderinfo;
import com.example.demo.entity.Orders;

import java.util.List;

public interface OrderService {
    //查看所有订单
    List<Orderinfo> getAll(int uid);
    //添加订单
    Orders addInfo(Orders order);
    //删除订单
    int deleteInfo(int oid);
}
