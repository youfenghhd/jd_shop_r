package com.example.demo.mapper;

import com.example.demo.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface CartMapper {
    //获取购物车信息
    List<Cart> getAll();
    //添加订单
    int addInfo(Cart cart);
    //删除订单信息
    int deleteInfo(int caid);
}
