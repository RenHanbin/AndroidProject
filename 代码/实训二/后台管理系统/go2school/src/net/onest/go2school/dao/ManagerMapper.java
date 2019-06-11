package net.onest.go2school.dao;

import org.apache.ibatis.annotations.Param;

import net.onest.go2school.entity.Manager;

public interface ManagerMapper {
	
	//登录
	public Manager login(@Param("managerName") String managerName,
			@Param("managerPassword") String managerPassword);
	
	//修改信息
	public int updateManagerById(Manager manager);

}
