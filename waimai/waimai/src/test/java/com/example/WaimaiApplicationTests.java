package com.example;

import com.example.entity.Employee;
import com.example.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class WaimaiApplicationTests {
    @Autowired
    EmployeeServiceImpl employeeService;
    
    @Test
    void contextLoads() {
        String s = "$2a$10$oQpj4laDLatd/GzIo.4oR.3m9Fl8NxsrN11seE4iwpTCb31E78Dqm";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches("123456", s);
        System.out.println("matches = " + matches);
    }
    
    @Test
    void dao(){
        Employee employee = employeeService.getById(1);
        System.out.println("employee = " + employee);
    }
    
}
