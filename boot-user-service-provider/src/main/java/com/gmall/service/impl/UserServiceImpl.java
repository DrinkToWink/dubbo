package com.gmall.service.impl;

import java.util.Arrays;
import java.util.List;
import com.gmall.bean.UserAddress;
import com.gmall.service.UserService;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service//暴露服务
@Component//不注入进接口（就是把这个注解注释掉）也不报错，不知道为啥
public class UserServiceImpl implements UserService {

	//@HystrixCommand（这个注解不知道有啥作用）
	public List<UserAddress> getUserAddressList(String userId) {
		// TODO Auto-generated method stub
		System.out.println("20882");
		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		if(Math.random()>0.5) {
//			throw new RuntimeException();
//		}
		return Arrays.asList(address1,address2);
	}

}
