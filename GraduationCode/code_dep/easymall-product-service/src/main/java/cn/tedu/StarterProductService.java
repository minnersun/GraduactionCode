package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.tedu.product.mapper")
public class StarterProductService {
	public static void main(String[] args) {
		SpringApplication.run(StarterProductService.class, args);
	}
	//在启动类中可以按照一个配置类来编写所有需要的配置内容
}






