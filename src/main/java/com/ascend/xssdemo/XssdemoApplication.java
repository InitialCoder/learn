package com.ascend.xssdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class XssdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XssdemoApplication.class, args);
	}
}
