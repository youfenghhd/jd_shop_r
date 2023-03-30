package com.example.demo.mapper;

import com.example.demo.entity.Indent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndentMapper {

    List<Indent> getAll(int uid);

    int addIndent(Indent indent);

    int deleteInfo(int oid);
}
