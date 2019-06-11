package net.onest.go2school.service;

import net.onest.go2school.entity.Manager;



public interface ManagerService {
	
	//管理员登录
	public Manager login(String managerName,String managerPassword);
	
	//修改管理员信息
	public int updateManagerById(Manager manager);

}
