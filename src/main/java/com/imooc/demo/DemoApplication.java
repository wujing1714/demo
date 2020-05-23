package com.imooc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = {"com.imooc.demo.mapper","com.imooc.demo.dao"})
//开启定时任务
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
