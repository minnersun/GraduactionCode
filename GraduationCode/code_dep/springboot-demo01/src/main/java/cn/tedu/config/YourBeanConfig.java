package cn.tedu.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.tedu.condition.ConditionA;
import cn.tedu.pojo.YourBean;

//配置类的注解,这个类的对象,相当于一个独立xml文件加载的内容
@Configuration
//@ConditionalOnClass({ConditionB.class})
@ConfigurationProperties("springboot.conf.test")
public class YourBeanConfig {
	//模拟生成的一个bean标签对象
	//<bean id="initMyBean" class="cn.tedu.pojo.MyBean"
	private String url;
	private String username;
	private String password;
	private String driver;
	@Bean
	public YourBean initMyBean(){
		YourBean yb=new YourBean();
		yb.setUsername(username);
		yb.setPassword(password);
		yb.setDriver(driver);
		yb.setUrl(url);
		return yb;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
