

# springboot-mybatis简单整合

## 需要的依赖

```java
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.30</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.0</version>
        </dependency>
```

## 配置文件

```properties
spring.datasource.username=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.password=123456
spring.datasource.url=jdbc:mysql://localhost:3306/springboot_mybatis
mybatis.mapper-locations=classpath:mapper/*Mapper.xml
#加了数据库连接的依赖之后必须自定除password以外的三个属性，不指定无法启动springboot项目
```

## 使用注解

@Mapper与@Select等注解在mybatis-spring-boot-starter中，使用注解开发mapper文件

例：@Select("select * from student")在这个sql语句中指定了操作的表名

```java
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

```

实体类

set与get省略了

```java
package com.example.demo.entity;
public class Student {
    private String name;
    private Integer age;
    private Integer id;}
```

contriller类简单查询与添加方法

```java
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

```

