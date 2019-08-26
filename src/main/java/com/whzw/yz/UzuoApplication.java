package com.whzw.yz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.whzw.yz.mapper")
@EnableTransactionManagement
@EnableScheduling
public class UzuoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UzuoApplication.class, args);
	}

}
