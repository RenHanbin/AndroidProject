package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;
import servlet.DataBase;

public class MyUserDao {
	public UserBean getUser(int userId){
		UserBean user=new UserBean();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select user_id,user_img,user_name,user_sex,user_tel,user_email from user where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			rs=pre.executeQuery();
			while(rs.next()) {
				System.out.print("UserDao:已经查询出user");
				user.setUserId(rs.getInt(1));
				user.setUserImg(rs.getString(2));
				user.setUserName(rs.getString(3));
				user.setUserSex(rs.getString(4));
				user.setUserTel(rs.getString(5));
				user.setUserEmail(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	//获取钱包金额
	public UserBean getUserBalance(int userId){
		UserBean user=new UserBean();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select user_id,user_balance from user where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			rs=pre.executeQuery();
			while(rs.next()) {
				System.out.print("UserDao:已经查询出user");
				user.setUserId(rs.getInt(1));
				user.setUserBalance(rs.getDouble(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	//更改用户信息
	public void changeUser(int userId,String userName, String userSex, String userPhone, String userEmail) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			int rs= 0;
			String sql="update user\r\n" + 
					"set user_name='"+userName+"',user_sex='"+userSex+"',user_tel='"+userPhone+"',user_email='"+userEmail+"'\r\n" + 
					"where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			rs=pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//粉丝人数
	public int getFansNum(int userId) {
		// TODO Auto-generated method stub
		int fansNum=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select count(follow_user_id) from follow where followed_user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			rs=pre.executeQuery();
			while(rs.next()) {
				fansNum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fansNum;
	}

	//我关注的人数
	public int getAttenNum(int userId) {
		int attenNum=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select count(*) from follow where follow_user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			rs=pre.executeQuery();
			while(rs.next()) {
				attenNum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attenNum;
	}
}
