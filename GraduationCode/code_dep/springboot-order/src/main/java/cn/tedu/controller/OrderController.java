package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.domain.User;
import cn.tedu.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService ouService;
	
	@RequestMapping("order/pay")
	public Integer orderPayUserUpdatePoint(String orderId){
		try{
			ouService.orderPayUserUpdatePoint(orderId);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}

