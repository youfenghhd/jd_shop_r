package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Indent {
    private int uid;
    private int oid;
    private String orderno;
    private int oprice;
    private String ctime;
    private String orderinfo;
    private String oaddress;
}
