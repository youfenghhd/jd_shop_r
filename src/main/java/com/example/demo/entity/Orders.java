package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;


@Data
@ToString


public class Orders {

  private int oid;
  private int uid;
  private int cid;
  private java.sql.Timestamp otime;
  private int oprice;

}
