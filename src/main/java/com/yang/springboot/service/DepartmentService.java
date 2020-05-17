package com.yang.springboot.service;

import com.yang.springboot.bean.Department;
import com.yang.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(value = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询："+id+" 号部门");
        return departmentMapper.getDeptById(id);
    }
}
