package cn.tedu.mapper;

import cn.tedu.domain.User;

public interface UserMapper {

	User queryUser(String userId);

	void updateUser(User user);

}
