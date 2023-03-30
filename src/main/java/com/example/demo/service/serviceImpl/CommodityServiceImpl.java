package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Commodity;
import com.example.demo.mapper.CommodityMapper;
import com.example.demo.service.CommodityService;
import com.example.demo.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<Commodity> findCommodity() {

        List<Commodity> commodity = commodityMapper.findCommodity();
        for (Commodity com:commodity) {
            com.setImage(ServletUtils.getImageUrl(com.getImage()));
//            System.out.println("***************"+com.getImage()+"****************");
        }
        return commodity;
    }

    @Override
    public List<Commodity> findCname(String cname,String cjude) {
        return commodityMapper.findCname(cname,cjude);
    }

    @Override
    public int changeCjude(int cid, String cjude) {
        return commodityMapper.changeCjude(cid,cjude);
    }

    @Override
    public int save(Commodity com) {
        return commodityMapper.save(com);
    }

    @Override
    public int update(Commodity com) {
        return commodityMapper.update(com);
    }

    @Override
    public Commodity byName(Commodity commodity) {
        return commodityMapper.byName(commodity);
    }

}
