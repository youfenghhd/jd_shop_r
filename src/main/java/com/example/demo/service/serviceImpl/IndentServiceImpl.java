package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Indent;
import com.example.demo.mapper.IndentMapper;
import com.example.demo.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {

    @Autowired
    IndentMapper indentMapper;

    @Override
    public List<Indent> getAll(int uid) {
        return indentMapper.getAll(uid);
    }

    @Override
    public int addIndent(Indent indent) {
        return indentMapper.addIndent(indent);
    }

    @Override
    public int deleteInfo(int oid) {
        return indentMapper.deleteInfo(oid);
    }
}
