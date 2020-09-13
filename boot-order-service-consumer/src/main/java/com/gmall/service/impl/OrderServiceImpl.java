package com.gmall.service.impl;

import java.util.Arrays;
import java.util.List;
import com.gmall.bean.UserAddress;
import com.gmall.service.OrderService;
import com.gmall.service.UserService;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 		1）、导入dubbo依赖（2.6.2）\操作zookeeper的客户端(curator)
 * 		2）、配置服务提供者
 * 
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 * @author lfy
 *
 */
@Component
public class OrderServiceImpl implements OrderService {

	//dubbo直连@Reference(url="127.0.0.1:20882")UserService userService;
	//dubbo的负载均衡机制为random也就是随机，默认也是这个
	@Reference(loadbalance="random",timeout=1000) UserService userService;

	//@HystrixCommand(fallbackMethod="hello")（应该是做限流控制的）
	public List<UserAddress> initOrder(String userId) {
		// TODO Auto-generated method stub
		System.out.println("用户id："+userId);
		//1、查询用户的收货地址
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		return addressList;
	}

	public List<UserAddress> hello(String userId) {
		// TODO Auto-generated method stub
		return Arrays.asList(new UserAddress(10, "测试地址", "1", "测试", "测试", "Y"));
	}
	

}
