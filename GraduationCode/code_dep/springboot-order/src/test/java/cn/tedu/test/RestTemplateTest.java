package cn.tedu.test;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cn.tedu.domain.User;

public class RestTemplateTest {
	@Test
	public void test(){
		//创建一个发起请求的RestTemplate
		RestTemplate client=new RestTemplate();
		//发起get请求
		/*发起请求地址url
		 *responseType,反射类型Class
		 *	接收的响应结果,String.class
		 * 
		 */
		User responseBody = client.
				getForObject("http://www.order.com/user/query/point?userId=1",
				User.class);
		ResponseEntity<User> response = client.getForEntity("http://127.0.0.1:8091/user/query/point?userId=1",
				User.class);
		response.getHeaders();
		response.getStatusCode();
		User user1 = response.getBody();
		//还可以对字符串做反序列化(json)
	/*	String responseBody = client.
				getForObject("https://www.jd.com",
				String.class);*/
		System.out.println(responseBody);
		System.out.println(user1);
		
	}
}
