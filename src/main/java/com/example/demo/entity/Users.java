package com.example.demo.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Users {

  private int uid;
  private String uname;
  private String password;
  private String gender;
  private String address;
}
