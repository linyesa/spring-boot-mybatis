package com.example.demo.dao;

import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student")
    List<Student> selectAll();

    @Select("select * from student where id = #{id}")
    Student selectById(@Param("id") int id);

    @Insert("insert into student(name,age) values (#{name},#{age})")
    Boolean insertUser(String name, int age);

}

