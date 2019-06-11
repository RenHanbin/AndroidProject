package net.onest.go2school.dao;

import java.util.List;

import net.onest.go2school.entity.Question;
import net.onest.go2school.entity.User;


public interface UserMapper {

	//查找所有用户
	public List<User> findAllUsers();
	
	//修改用户信息
	public int updateUser(User user);
	
	
	//根据用户ID查找对应用户的详细信息
		public User findUserById2(int userId);
		
		//根据用户ID找用户的粉丝数
		public User findUserById1(int userId);
		
		//根据用户ID找用户的关注人数
		public User findUserById(int userId);
		
		//根据用户ID找关注人的ID列表
		public List<User> findfollowListById(int userId);


}
