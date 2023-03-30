package com.example.demo.service;


import com.example.demo.entity.Commodity;

import java.util.List;

public interface CommodityService {
    //查找所有商品
    //查找所有
    List<Commodity> findCommodity();

    //根据条件模糊查找
    List<Commodity> findCname(String cname, String cjude);
    //修改状态的方法
    int changeCjude(int cid, String cjude);
    // 添加商品
    int save(Commodity com);

    // 修改商品
    int update(Commodity com);

    Commodity byName(Commodity commodity);
}
