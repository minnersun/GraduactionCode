package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.domain.User;
import cn.tedu.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService ouService;
	@RequestMapping("user/query/point")
	public User queryUserPoint(String userId){
		
		User user=ouService.queryUser(userId);
		return user;
	}
	//根据用户id,支付金额编写积分新增
	@RequestMapping("user/update/points")
	public Integer updateUserPoint(String userId,Integer orderMoney){
		try{
			ouService.updateUserPoint(userId,orderMoney);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}

