package com.example.demo.entity;

import lombok.Data;

/**
 * @ClassName 和阿里交互的对象（用于前端和后端交互，字段名最好严格按照规范）
 * @Author 春哥 @Version 1.0
 * 功能说明：
 **/
@Data
public class AliPay {
    private String traceNo; //我们的订单号
    private Double totalAmount; //总金额
    private String subject; //主题
    private String alipayTraceNo;//阿里的流水号
}