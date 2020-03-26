package cn.tedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.domain.User;
import cn.tedu.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper ouMapper;
	public User queryUser(String userId) {
		return ouMapper.queryUser(userId);
	}
	public void updateUserPoint(String userId, Integer orderMoney) {
		//第一种lev不管lev,直接根据金额积分,
		//查询已有积分
		User user = ouMapper.queryUser(userId);
		/*user.setPoints(user.getPoints()+orderMoney);
		ouMapper.updateUser(user);*/
		//第二种 lev=0,lev=1
		int points=0;
		if(user.getLev()==0){//5倍
			points=orderMoney*5;
		}
		user.setPoints(user.getPoints()+points);
		ouMapper.updateUser(user);
	}
}
