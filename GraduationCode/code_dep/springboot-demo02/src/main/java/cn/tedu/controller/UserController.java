package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.entity.User;
import cn.tedu.mapper.UserMapper;

@RestController
public class UserController {
	@Autowired
	private UserMapper userMapper;
	@RequestMapping("user")
	public User queryUser(String userId){
		User user=userMapper.queryUser(userId);
		return user;
	}
}
