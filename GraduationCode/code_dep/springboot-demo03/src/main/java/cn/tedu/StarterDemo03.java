package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class StarterDemo03 {
	public static void main(String[] args) {
		SpringApplication.run(StarterDemo03.class, args);
	}
	//访问:9001/index,返回index.jsp
	@RequestMapping("index")
	public String goIndex(){
		return "index";
	}
}
