package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	//通过属性，读取server.port
	@Value("${server.port}")
	private String port;
	@RequestMapping("hello")
	public String sayHi(String name){
		return "hello "+name+",i am from "+port;
	}
}




