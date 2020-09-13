package com.gmall;

import com.gmall.bean.UserAddress;
import com.gmall.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

import java.util.List;

@EnableDubbo(scanBasePackages = "com.gmall")//开启dubbo的注解扫描
//@EnableHystrix(这个不知道是干啥的)
@SpringBootApplication
public class BootOrderServiceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootOrderServiceConsumerApplication.class, args);
//		ConfigurableApplicationContext ac = SpringApplication.run(BootOrderServiceConsumerApplication.class, args);
//		OrderService bean = ac.getBean(OrderService.class);
//		List<UserAddress> userAddresses = bean.initOrder("003");
//		for (UserAddress us:userAddresses){
//			System.out.println(us);
//		}

	}
}
