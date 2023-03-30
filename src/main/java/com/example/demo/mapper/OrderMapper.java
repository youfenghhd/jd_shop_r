package com.example.demo.mapper;

import com.example.demo.entity.Orderinfo;
import com.example.demo.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    //查看所有订单
    List<Orderinfo> getAll(int uid);
    //添加订单
    Orders addInfo(Orders Orders);
    //删除订单
    int deleteInfo(int oid);
}
