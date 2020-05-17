package com.yang.springboot;

import com.yang.springboot.bean.Employee;
import com.yang.springboot.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot09CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;    //操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate;    //k-v都是对象的

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void test1(){
        //保存数据
//        stringRedisTemplate.opsForValue().set("msg","hello");
//
//        该方法标识从list的左边（上边，或者说list的头部）压入数据。如果不存在该key，则会创建该key
//        stringRedisTemplate.opsForList().leftPush("myList","1");
//        stringRedisTemplate.opsForList().leftPush("myList","2");

        //取出数据
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    //测试保存对象
    @Test
    public void test2(){
        Employee emp = employeeMapper.getEmp(2);
        empRedisTemplate.opsForValue().set("emp-02",emp);
    }

    @Test
    void contextLoads() {
    }

}
