package com.ascend.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//如果service实现类中有事物注解，则此处要添加此注解
@SpringBootApplication
//@MapperScan("com.ascend.demo.*.dao")//若不加这个注解，则dao类里面的mapper 类需要加上@Mapper注解
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
