package net.onest.go2school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.onest.go2school.dao.ManagerMapper;
import net.onest.go2school.entity.Manager;
import net.onest.go2school.service.ManagerService;


@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerMapper managerMapper;

	//登录
	@Override
	public Manager login(String managerName,String managerPassword) {
		// TODO Auto-generated method stub
		return managerMapper.login(managerName,managerPassword);
	}

	
	//修改管理员信息
	@Override
	public int updateManagerById(Manager manager) {
		int result=managerMapper.updateManagerById(manager);
	
		return result;
	} 
	

	
	

}
