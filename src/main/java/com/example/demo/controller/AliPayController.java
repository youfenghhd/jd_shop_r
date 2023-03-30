package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.demo.config.AlipayConfig;
import com.example.demo.entity.AliPay;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName
 * @Author 春哥 @Version 1.0
 * 功能说明：
 **/
@RestController
@RequestMapping("/alipay")
public class AliPayController {
    //阿里网关地址
    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    //数据格式
    private static final String FORMAT = "JSON";
    //编码格式
    private static final String CHARSET = "UTF-8";
    //签名方式
    private static final String SIGN_TYPE = "RSA2";
    @Resource
    private AlipayConfig alipayConfig;

    @GetMapping("/pay") // ?subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(AliPay aliPay, HttpServletResponse response) throws IOException {
        System.out.println(aliPay);
        System.out.println("alipayConfig:" + alipayConfig);
//1.创建client，通过阿里SDK提供的client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(), FORMAT, CHARSET, alipayConfig.getAlipayPublicKey(), SIGN_TYPE);
//2.创建request，并设置request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", aliPay.getTraceNo());//我们自己生成的订单编号
        bizContent.put("total_amount", aliPay.getTotalAmount());//订单总金额
        bizContent.put("subject", aliPay.getSubject());//支付名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");//固定配置
        request.setBizContent(bizContent.toString());
//执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody();//调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
//设置响应结果，将返回的内容写出到浏览器
        response.setContentType("text/html;charset=" + CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 支付宝异步回调【必须是POST】
     *
     * @return
     */
    @PostMapping("/notify")
    public String payNotify(HttpServletRequest request) throws AlipayApiException {
        System.out.println("异步回调了。" + request);
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("-------------支付宝异步回调----");
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            System.out.println("-----params-----------");
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                System.out.println(name + " " + request.getParameter(name));
            }
            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");//支付时间
            String alipayTradeNo = params.get("trade_no");
            String sign = params.get("sign");//拿到签名
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, alipayConfig.getAlipayPublicKey(), "UTF-8");//验证签名
//支付宝验签
            if (checkSignature) {
//验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
// 查询订单
//TODO 支付成功，操作数据库，创建对应订单，修改对应商品数据
            }
        }
        return "success";
    }
}