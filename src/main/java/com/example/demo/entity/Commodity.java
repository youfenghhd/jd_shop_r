package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Commodity {

  private int cid;
  private String cname;
  private String cinfo;
  private int cprice;
  private java.sql.Timestamp ctime;
  private String cjude;
  private int cnumber;
  private String image;
}
