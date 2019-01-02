package com.ascend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//如果service实现类中有事物注解，则此处要添加此注解
@SpringBootApplication
@EnableCaching
//@MapperScan("com.ascend.demo.*.dao.*dao")//若不加这个注解，则dao类里面的mapper 类需要加上@Mapper注解
@ServletComponentScan//自动扫描和注册 @WebServlet, @WebFilter, and @WebListener annotated classes 的实体类到spring 容器
@EnableConfigurationProperties//指定此类需要装载信息
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
