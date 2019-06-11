package net.onest.go2school.service;

import java.util.List;

import net.onest.go2school.entity.User;



public interface UserService {
	
	//查找所有用户
	public List<User> findAllUsers();
	
	public User findUserById(int userId);//关注人数
	
	public User findUserById1(int userId);//粉丝数
	
	public User findUserById2(int userId);//用户详细信息
	
	public List<User> findfollowListById(int userId);//具体关注人信息

	
	//修改用户信息
	public int updateUser(User user);

}
