package cn.tedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.tedu.domain.Order;
import cn.tedu.mapper.OrderMapper;

@Service
public class OrderService {
	@Autowired
	private RestTemplate client;
	@Autowired
	private OrderMapper ouMapper;
	public void orderPayUserUpdatePoint(String orderId) {
		Order order=ouMapper.queryOrder(orderId);
		//正好打折日
		Integer orderMoney=order.getOrderMoney();
		Integer payMoney=(int) (orderMoney*0.8);
		System.out.println("您的用户："+order.getUserId()+"支付金额："+payMoney);
		//支付完成
		//通知user系统做积分
		//根据接口文件定义,将支付的金额,和支付的用户信息传递给user系统
		//RestTemplate client=new RestTemplate();
		//根据接口访问 /user/update/points?userId&orderMoney
		String url="http://usertest/user/update"
				+ "/points?userId="+order.getUserId()
				+"&orderMoney="+payMoney;
		//发起请求,交给用户系统处理积分逻辑,数据最终一致性
		client.getForObject(url, Integer.class);
	}

}
