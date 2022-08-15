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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println("encode = " + encode);
        boolean matches = encoder.matches("123456", encode);
        System.out.println("matches = " + matches);
    }
    
    @Test
    void dao(){
        Employee employee = employeeService.getById(1);
        System.out.println("employee = " + employee);
    }
    
}
