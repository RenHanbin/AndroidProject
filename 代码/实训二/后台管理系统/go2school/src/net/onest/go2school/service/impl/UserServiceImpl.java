package net.onest.go2school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.onest.go2school.dao.UserMapper;
import net.onest.go2school.entity.User;
import net.onest.go2school.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	//查找所有用户
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userMapper.findAllUsers();
	}
	
	
	
	//修改用户信息
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
	
		return userMapper.updateUser(user);
	}
	
	@Override
	public User findUserById(int userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserById(userId);
		
	}
	@Override
	public User findUserById1(int userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserById1(userId);
	}
	@Override
	public List<User> findfollowListById(int userId) {
		// TODO Auto-generated method stub
		return userMapper.findfollowListById(userId);
	}
	@Override
	public User findUserById2(int userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserById2(userId);
	}
	


}
