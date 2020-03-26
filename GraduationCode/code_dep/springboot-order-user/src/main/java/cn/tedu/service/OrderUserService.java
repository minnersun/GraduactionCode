package cn.tedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.domain.Order;
import cn.tedu.domain.User;
import cn.tedu.mapper.OrderUserMapper;

@Service
public class OrderUserService {
	@Autowired
	private OrderUserMapper ouMapper;
	public User queryUser(String userId) {
		return ouMapper.queryUser(userId);
	}
	public void orderPayUserUpdatePoint(String orderId) {
		//查询支付金额,支付
		Order order=ouMapper.queryOrder(orderId);
		Integer orderMoney=order.getOrderMoney();
		System.out.println("当前用户:"+order.getUserId()+"支付金额:"+orderMoney);
		//根据金额,根据积分逻辑(lev=0时,积分1倍,=1积分5倍 lev==2)
		//需要获取当前订单对应user的lev
		User user = ouMapper.queryUser(order.getUserId());
		Integer lev=user.getLev();
		//判断积分的分数
		Integer point=0;
		if(lev==0){//积分1倍
			point=orderMoney*1;
		}else if(lev==1){
			point=orderMoney*5;
		}
		//封装一个积分更新的对象
		user.setPoints(user.getPoints()+point);
		ouMapper.updateUserPoints(user);
		//update t_user set points=#{points}
		//where user_id=#{userId};
	}

}
