package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

  private int uid;
  private String uname;
  private String upwd;
  private String uacc;
  private String sex;
  private int age;
  private String utype;
  private int ubalance;

}
