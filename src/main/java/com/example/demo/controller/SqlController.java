package com.example.demo.controller;

import com.example.demo.dao.StudentMapper;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SqlController {
    @Autowired
    StudentMapper studentMapper;
    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        System.out.println(studentMapper.selectAll());
        return "test hello";
    }
    @GetMapping("/select/{id}")
    @ResponseBody
    public Student selectById(@PathVariable("id")int id){
        return studentMapper.selectById(id);
    }
    @ResponseBody
    @GetMapping("/insert/{name}/{age}")
    public Boolean insert(@PathVariable("name")String name,@PathVariable("age")int age){
        return studentMapper.insertUser(name,age);
    }
}
