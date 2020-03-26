package cn.tedu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.pojo.YourBean;
import cn.tedu.service.HelloService;
@RestController
public class HelloController {
	@Autowired
	private YourBean bean;
	@Autowired
	private HelloService helloService;
	@RequestMapping("hello")
	public String sayHi(String name){
		return "controller:"+helloService.sayHi(name);
	}
}
