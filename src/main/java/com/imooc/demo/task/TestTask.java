package com.imooc.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestTask {

    @Scheduled(fixedRate = 3000)
    public void test(){
        System.out.println(new Date());
    }
}
