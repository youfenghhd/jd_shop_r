package com.example.demo.controller;

import com.example.demo.entity.Orderinfo;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("订单接口")
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/getInfo")
    @ApiOperation(value = "获取订单列表", notes = "订单列表")
    public ResultUtils<?> getAll(@RequestBody Orders orders) {
        List<Orderinfo> all = orderService.getAll(orders.getUid());
        if (!all.isEmpty()) {
            return ResultUtils.success(all);
        } else {
            return ResultUtils.failed();
        }
    }

    @PostMapping("/addInfo")
    @ApiOperation(value = "添加订单信息", notes = "订单信息")
    public ResultUtils<?> addInfo(@RequestBody Orders order) {
        Orders bean = orderService.addInfo(order);
        if (bean != null) {
            return ResultUtils.success(bean);
        } else {
            return ResultUtils.failed();
        }
    }

    @DeleteMapping("/deleteInfo")
    @ApiOperation(value = "删除订单", notes = "删除订单")
    public ResultUtils<?> deleteInfo(@RequestBody Orders order) {
        int i = orderService.deleteInfo(order.getCid());
        if (i > 0) {
            return ResultUtils.success(order.getCid());
        } else {
            return ResultUtils.failed();
        }
    }
}
