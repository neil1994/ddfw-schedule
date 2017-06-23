package com.dxhy.dispatch;

import com.dxhy.dispatch.manage.constants.UrlConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.dxhy.dispatch.manage.dao"})
@EnableConfigurationProperties({UrlConstants.class})
public class DdfwScheduleApplication {
	public static void main(String[] args) {
		SpringApplication.run(DdfwScheduleApplication.class, args);
	}
}
