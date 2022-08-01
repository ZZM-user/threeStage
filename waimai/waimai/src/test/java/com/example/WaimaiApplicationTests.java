package com.example;

import com.example.entity.Employee;
import com.example.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WaimaiApplicationTests {
    @Autowired
    EmployeeServiceImpl employeeService;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    void dao(){
        Employee employee = employeeService.getById(1);
        System.out.println("employee = " + employee);
    }
    
}
