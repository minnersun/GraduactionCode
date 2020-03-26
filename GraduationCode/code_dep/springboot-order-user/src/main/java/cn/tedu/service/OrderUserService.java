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
		//��ѯ֧�����,֧��
		Order order=ouMapper.queryOrder(orderId);
		Integer orderMoney=order.getOrderMoney();
		System.out.println("��ǰ�û�:"+order.getUserId()+"֧�����:"+orderMoney);
		//���ݽ��,���ݻ����߼�(lev=0ʱ,����1��,=1����5�� lev==2)
		//��Ҫ��ȡ��ǰ������Ӧuser��lev
		User user = ouMapper.queryUser(order.getUserId());
		Integer lev=user.getLev();
		//�жϻ��ֵķ���
		Integer point=0;
		if(lev==0){//����1��
			point=orderMoney*1;
		}else if(lev==1){
			point=orderMoney*5;
		}
		//��װһ�����ָ��µĶ���
		user.setPoints(user.getPoints()+point);
		ouMapper.updateUserPoints(user);
		//update t_user set points=#{points}
		//where user_id=#{userId};
	}

}
