package com.example.demo.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Role {

  private long rid;
  private String rname;
  private String rdesc;
}
