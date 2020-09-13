package com.gmall.config;

import java.util.ArrayList;
import java.util.List;
import com.gmall.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

//在provider.xml文件中的每一个标签在配置类中的方法都能配置（配置类的标签对应方法的返回值）
@Configuration
public class MyDubboConfig {

	//<dubbo:application name="boot-user-service-provider"></dubbo:application>
	//ApplicationConfig类型的返回值，在标签中就对应<dubbo:application></dubbo:application>
	//ApplicationConfig对象可以设置的参数（这里就是applicationConfig.setName），
	//就相当于name="boot-user-service-provider"，每个方法都是这样
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("boot-user-service-provider");
		return applicationConfig;
	}
	
	//<dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol("zookeeper");
		registryConfig.setAddress("127.0.0.1:2181");
		return registryConfig;
	}
	
	//<dubbo:protocol name="dubbo" port="20882"></dubbo:protocol>
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName("dubbo");
		protocolConfig.setPort(20882);
		return protocolConfig;
	}
	
	/**
	 *<dubbo:service interface="com.atguigu.gmall.service.UserService" 
		ref="userServiceImpl01" timeout="1000" version="1.0.0">
		<dubbo:method name="getUserAddressList" timeout="1000"></dubbo:method>
	</dubbo:service>
	 */
	//springboot会自动将UserService接口的在spring容器中的实现类自动注入进去
	//这个userService不注入进去也不会报错，不知道为啥
	@Bean
	public ServiceConfig<UserService> userServiceConfig(UserService userService){
		ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setInterface(UserService.class);
		serviceConfig.setRef(userService);//不设置也不报错，不知道为啥
		//System.out.println(userService==null);//不注入进去也不为空，不知道为啥
		serviceConfig.setVersion("1.0.0");
		
		//配置每一个method的信息
		MethodConfig methodConfig = new MethodConfig();
		methodConfig.setName("getUserAddressList");
		methodConfig.setTimeout(1000);
		
		//将method的设置关联到service配置中
		List<MethodConfig> methods = new ArrayList<>();
		methods.add(methodConfig);
		serviceConfig.setMethods(methods);
		
		//ProviderConfig
		//MonitorConfig
		
		return serviceConfig;
	}

}
