package cn.tedu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//springboot的核心注解
@SpringBootApplication
public class StarterDemo01 {
	//当前springboot中唯一的一个入口方法main
	public static void main(String[] args) {
		//SpringBoot体工程启动对象
		SpringApplication.run(StarterDemo01.class, args);
	}
}
