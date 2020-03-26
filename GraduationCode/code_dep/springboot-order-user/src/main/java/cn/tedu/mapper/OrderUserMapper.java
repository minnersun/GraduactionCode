package cn.tedu.mapper;

import cn.tedu.domain.Order;
import cn.tedu.domain.User;

public interface OrderUserMapper {

	User queryUser(String userId);

	Order queryOrder(String orderId);

	void updateUserPoints(User user);

}
