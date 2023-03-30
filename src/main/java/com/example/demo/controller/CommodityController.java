package com.example.demo.controller;


import com.example.demo.entity.Commodity;
import com.example.demo.service.CommodityService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//跨域注解
@CrossOrigin
@Api("商品接口")
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    //注入service
    @Autowired
    private CommodityService commodityService;

    //查找所有商品
    @GetMapping(value = "/findCommodity")
    @ApiOperation(value = "查詢所有商品",notes = "商品查詢")
    public ResultUtils<?> findCommodity(){
        List<Commodity> commodityList= commodityService.findCommodity();
        if (!commodityList.isEmpty()){
            return ResultUtils.success(commodityList);
        }else {
            return ResultUtils.failed();
        }
    }

    @PostMapping(value = "/findCname")
    @ApiOperation(value = "按商品名查詢",notes = "商品名查詢")
    public ResultUtils<?> findCname(@RequestBody Commodity commodity){
        List<Commodity> commodityList = commodityService.findCname(commodity.getCname(),commodity.getCjude());
        if(!commodityList.isEmpty()){
            return ResultUtils.success(commodityList);
        }else {
            return ResultUtils.failed();
        }
    }

    @PostMapping(value = "/byName")
    @ApiOperation(value = "按商品名查詢",notes = "商品名查詢")
    public ResultUtils<?> byName(@RequestBody Commodity commodity){
        List<Commodity> commodityList = commodityService.findCname(commodity.getCname(),commodity.getCjude());
        if(!commodityList.isEmpty()){
            return ResultUtils.success(commodityList);
        }else {
            return ResultUtils.failed();
        }
    }


    //修改状态
    @PostMapping(value = "/changeCjude")
    @ApiOperation(value = "修改状态",notes = "修改状态")
    public ResultUtils<?> changeCjude(@RequestBody Commodity commodity) {
        String cjude1 = "";
        if (commodity.getCjude().equals("上架")) {
            cjude1 = "下架";
        } else {
            cjude1 = "上架";
        }
        int judge = commodityService.changeCjude(commodity.getCid(), cjude1);
        if (judge > 0) {
            return ResultUtils.success(commodity.getCid());
        } else {
            return ResultUtils.failed();
        }
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "添加商品",notes = "添加商品")
    public ResultUtils<?> save(@RequestBody Commodity com) {
        int save = commodityService.save(com);
        if (save>0){
            return ResultUtils.success(com.getCid());
        }
        return ResultUtils.failed();
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "更新商品",notes = "更新商品")
    public ResultUtils<?> update(@RequestBody Commodity commodity){
        int update = commodityService.update(commodity);
        if (update>0){
            return ResultUtils.success(commodity.getCname());
        }
        return ResultUtils.failed();
    }
}
