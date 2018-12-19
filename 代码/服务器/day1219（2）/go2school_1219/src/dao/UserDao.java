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
		public boolean addUser(String userName, String userPwd, String userTel) {
			// TODO Auto-generated method stub
			Connection conn=null;
			try {
				conn=DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="insert into user(user_name,user_password,user_tel,user_sex) values (?,?,?,?) ";
				pre=conn.prepareStatement(sql);
				pre.setString(1, userName);
				pre.setString(2, userPwd);
				pre.setString(3, userTel);
				pre.setString(4, "男");
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
}
