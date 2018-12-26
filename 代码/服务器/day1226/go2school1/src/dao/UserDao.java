package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;
import servlet.DataBase;

public class UserDao {

	//验证登录
		public UserBean LoginCheck(String userName,String userPassword) {
			UserBean user=new UserBean();
			Connection conn=null;
			try {
				conn=DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select user_id,user_name,user_password,user_tel,user_email,user_sex,user_balance from user where user_name=? and user_password=? ";
				pre=conn.prepareStatement(sql);
				pre.setString(1, userName);
				pre.setString(2, userPassword);
				res=pre.executeQuery();
				if(res.next()) {//表示该用户已经注册
					user.setUserId(res.getInt("user_id"));
					user.setUserName(res.getString("user_name"));
					user.setUserPassword(res.getString("user_password"));
					user.setUserTel(res.getString("user_tel"));
					user.setUserEmail(res.getString("user_email"));
					user.setUserSex(res.getString("user_sex"));
					user.setUserBalance(res.getDouble("user_balance"));
					return user;
				}else {//表示是未注册的用户
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		//用户注册
		public boolean addUser(String userName, String userPwd, String userTel, String userEmail) {
			// TODO Auto-generated method stub
			Connection conn=null;
			try {
				conn=DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="insert into user(user_name,user_password,user_tel,user_sex,user_email) values (?,?,?,?,?) ";
				pre=conn.prepareStatement(sql);
				pre.setString(1, userName);
				pre.setString(2, userPwd);
				pre.setString(3, userTel);
				pre.setString(4, "男");
				pre.setString(5, userEmail);
				int i=pre.executeUpdate();
				if(i>0) {
					return true;
				}else {
					return false;
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}

		//找回密码
		public String findPassword(String userTel) {
			String userPassword=null;
			Connection conn=null;
			try {
				conn=DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select user_password from user where user_tel=?";
				pre=conn.prepareStatement(sql);
				pre.setString(1, userTel);
				res=pre.executeQuery();
				if(res.next()) {
					userPassword=res.getString("user_password");
					System.out.println(userPassword);
					return userPassword;
				}else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userPassword;
		}

		/*
		 * 根据userId找到user
		 * */
		public UserBean getUserByUserId(int userId) {
			UserBean userbean=new UserBean();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select user_id,user_name,user_password,user_email,user_sex,user_tel,user_img,user_balance from user where user_id=?";
				pre=conn.prepareStatement(sql);
				pre.setInt(1,userId);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("UserDao:已经查询出user");	
					userbean.setUserId(res.getInt("user_id"));
					userbean.setUserName(res.getString("user_name"));
					userbean.setUserPassword(res.getString("user_password"));
					userbean.setUserEmail(res.getString("user_email"));
					userbean.setUserSex(res.getString("user_sex"));
					userbean.setUserTel(res.getString("user_tel"));
					userbean.setUserImg(res.getString("user_img"));
					userbean.setUserBalance(res.getDouble("user_balance"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userbean;
		}
		
		/*
		 * 根据userId修改userPassword
		 * */
		public void updateUserPasswordByUserId(int userId,String userPassword) {
			UserBean user=new UserBean();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="update user set user_password=? where user_id=?";
				pre=conn.prepareStatement(sql);
				pre.setString(1, userPassword);
				pre.setInt(2, userId);
				pre.executeUpdate();
				res=pre.getGeneratedKeys();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}

		//判断是否注册过
		public boolean ifRegistered(String userTel) {
			// TODO Auto-generated method stub
			boolean b;
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select * from user where user_tel=?";
				pre=conn.prepareStatement(sql);
				pre.setString(1,userTel);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("UserDao:已经查询出user");	
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}

}
