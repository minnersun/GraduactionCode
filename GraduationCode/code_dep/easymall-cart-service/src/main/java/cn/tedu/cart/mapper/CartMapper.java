package cn.tedu.cart.mapper;

import java.util.List;

import com.jt.common.pojo.Cart;

public interface CartMapper {

	List<Cart> queryMyCart(String userId);

	Cart queryExist(Cart cart);

	void saveCart(Cart cart);

	void updateNum(Cart cart);

	void deleteCart(Cart cart);

}
