package com.example.util;

import com.example.controller.front.FrontSmsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author： 17602
 * @Date： 2022/8/30 16:39
 * @Desc：定时任务
 **/
@Component
public class ScheduledUtil {
    
    @Autowired
    FrontSmsController frontSmsController;

// @Scheduled(fixedRate = 2100)
// public void scheduleJob() {
//     R r = frontSmsController.sendSms("18225472964");
//     System.out.println("1R = " + r);
// }
//
// @Scheduled(fixedRate = 2200)
// public void scheduleJob2() {
//     R r = frontSmsController.sendSms("18225472964");
//     System.out.println("2R = " + r);
// }
}
