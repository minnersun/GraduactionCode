package cn.tarena.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.tedu.condition.ConditionA;
import cn.tedu.controller.HelloController;
import cn.tedu.pojo.MyBean;

@Configuration
@ConditionalOnClass({ConditionA.class})
//@AutoConfigureAfter(YourBeanConfig.class)
public class ConditionalConfig {
	public ConditionalConfig(){
		System.out.println("条件满足创建条件配置对象");
	}
	@Bean
	//当容器中已经存在，可以创建一个YourBean对象时
	@ConditionalOnBean(HelloController.class)
	public MyBean initMyBeanPrimary(){
		return new MyBean();
	}
}
