package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Cart {
  private int caid;
  private int cid;
  private int uid;
  private String catime;
  private String cname;
  private int canumber;
}
