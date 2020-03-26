package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.domain.User;
import cn.tedu.service.OrderUserService;

@RestController//���ע��,ResponseBody Controller
public class OrderUserController {
	
	@Autowired
	private OrderUserService ouService;
	@RequestMapping("user/query/point")
	public User queryUserPoint(String userId){
		
		User user=ouService.queryUser(userId);
		return user;
	}
	//����֧���ͻ��ֵĸ���
	@RequestMapping("order/pay")
	public Integer orderPayUserUpdatePoint(String orderId){
		try{
			ouService.orderPayUserUpdatePoint(orderId);
			return 1;//�ɹ�����֧����������
		}catch(Exception e){
			e.printStackTrace();
			return 0;//����ʧ��
		}
	}
}

