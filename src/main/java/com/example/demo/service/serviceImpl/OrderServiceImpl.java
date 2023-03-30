package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Orderinfo;
import com.example.demo.entity.Orders;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Orderinfo> getAll(int uid) {
        return orderMapper.getAll(uid);
    }

    @Override
    public Orders addInfo(Orders order) {
        return orderMapper.addInfo(order);
    }

    @Override
    public int deleteInfo(int oid) {
        return orderMapper.deleteInfo(oid);
    }
}
