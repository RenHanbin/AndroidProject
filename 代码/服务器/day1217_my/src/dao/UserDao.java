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
				String sql="select user_name,user_password,user_tel,user_email,user_sex from user where user_name=? and user_password=? ";
				pre=conn.prepareStatement(sql);
				pre.setString(1, userName);
				pre.setString(2, userPassword);
				res=pre.executeQuery();
				if(res.next()) {//表示该用户已经注册
					user.setUserName(res.getString("user_name"));
					user.setUserPassword(res.getString("user_password"));
					user.setUserTel(res.getString("user_tel"));
					user.setUserEmail(res.getString("user_email"));
					user.setUserSex(res.getString("user_sex"));
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
		
}
