package com.example.demo.controller;

import com.example.demo.entity.Indent;
import com.example.demo.service.IndentService;
import com.example.demo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Api("订单描述")
@CrossOrigin
@RestController
@RequestMapping("/indent")
public class IndentController {

    @Autowired
    IndentService indentService;

    @PostMapping(value = "/getAll")
    @ApiOperation(value = "订单查询",notes = "订单查询")
    public ResultUtils<?> getInfo(@RequestBody Indent indent){
        List<Indent> all = indentService.getAll(indent.getUid());
        if (!all.isEmpty()){
            return ResultUtils.success(all);
        }else {
            return ResultUtils.failed();
        }
    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "添加订单", notes = "订单描述")
    public ResultUtils<?> insertInfo(@RequestBody Indent indent) {
        String format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINESE).format(new Date());
        indent.setCtime(format);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        indent.setOrderno(df.format(new Date()) + new Random().nextInt(100));
        System.out.println(indent+"****************************");
        int i = indentService.addIndent(indent);
        if (i > 0) {
            return ResultUtils.success(indent.getOrderno());
        } else {
            return ResultUtils.failed();
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除订单",notes = "删除订单")
    public ResultUtils<?> deleteInfo(@RequestBody Indent indent){
        int i = indentService.deleteInfo(indent.getOid());
        if (i>0){
            return ResultUtils.success(indent.getOrderno());
        }else {
            return ResultUtils.failed();
        }
    }
}
