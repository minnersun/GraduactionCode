package cn.tedu.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class HelloService {
	@Value("${username}")
	private String username;
	public String sayHi(String name) {
		return "service"+"hello springboot "+name;
	}
}
