package com.example.demo.service;

import com.example.demo.entity.Indent;

import java.util.List;

public interface IndentService {
    List<Indent> getAll(int uid);

    int addIndent(Indent indent);

    int deleteInfo(int oid);
}
