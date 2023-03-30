package com.example.demo.utils.thread;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class Person {
    private String name;
    public Person setName(String name) {
        this.name = name;
        return this;
    }
}