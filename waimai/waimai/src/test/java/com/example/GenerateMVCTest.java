package com.example;

import com.example.util.generate.GenerateMVC;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Author： 17602
 * @Date： 2022/8/1 19:49
 * @Desc：
 **/
@SpringBootTest
public class GenerateMVCTest {
    GenerateMVC generateMVC = new GenerateMVC();
    
    @Test
    void testReadConfig() {
        try {
            generateMVC.generate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
