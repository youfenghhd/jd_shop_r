package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Orderinfo {

  private int ofid;
  private int uid;
  private int cid;
  private String ofinfo;
  private String oftype;
}
