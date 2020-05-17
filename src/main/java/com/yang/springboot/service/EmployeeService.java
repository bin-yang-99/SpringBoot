package com.yang.springboot.service;

import com.yang.springboot.bean.Employee;
import com.yang.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


    @CacheConfig(cacheNames = "emp")    //抽取缓存的公共配置
    @Service
    public class EmployeeService {

    @Autowired
    EmployeeMapper mapper;

//    cacheNames与value等价
//    @Cacheable(cacheNames = {"emp"},key = "#root.methodName+'['+#id+']'")   //SpEL
//    @Cacheable(cacheNames = {"emp"},keyGenerator = "myKeyGenerator",condition = "#id>1",unless = "#id==2")
    @Cacheable(value = {"emp"},key = "#id")
    public Employee getEmp(Integer id){
        System.out.println("查询 "+id+" 号员工");
        return mapper.getEmp(id);
    }

//    key = "#emp.id":使用传入的参数的员工id
//    key = "#result.id"：使用返回后的id
    @CachePut(value = {"emp"},key = "#result.id")   //既调用方法，又更新缓存数据；同步更新缓存（一定会执行）
    public Employee updateEmp(Employee emp){
        System.out.println("update: "+emp);
        mapper.updateEmp(emp);
        return emp;
    }

    @CacheEvict(value = {"emp"},key = "#id")    //清除缓存
    public void deleteEmp(Integer id){
        System.out.println("delete: "+id);
//        int i = 10/0; //异常
    }

    //定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(key = "#lastName")
            },
            put = {
                    @CachePut(key = "#result.id"),
                    @CachePut(key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return mapper.getEmpByLastName(lastName);
    }
}
