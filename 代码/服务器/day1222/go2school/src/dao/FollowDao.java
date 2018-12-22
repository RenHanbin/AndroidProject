package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.FollowBean;
import bean.UserBean;
import servlet.DataBase;

public class FollowDao {
	//通过userId找到关注的人
	public List<FollowBean> getFollow(int userId){
		List<FollowBean> myFollowList=new ArrayList<FollowBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select follow_user_id,followed_user_id from follow where follow_user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			rs=pre.executeQuery();
			while(rs.next()) {
				FollowBean follow=new FollowBean();
				System.out.print("FollowDao:已经查询出follow");
				follow.setFollowUserId(rs.getInt(1));
				System.out.println("88888888888"+rs.getInt("followed_user_id"));
				UserBean user =new UserDao().getUserByUserId(rs.getInt("followed_user_id"));
				follow.setFollowedUser(user);
				myFollowList.add(follow);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myFollowList;

	}
	
	/*
	 * 删除关注
	 * 根据followUserId，followedUserId删除follew中的记录
	 * */
	public Boolean deteleFollew(int followUserId,int followedUserId){
		Connection conn=null;
		int i=0;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="delete from follow where follow_user_id=? and followed_user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, followUserId);
			pre.setInt(2, followedUserId);
			i=pre.executeUpdate();
		 
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
	
	//取消关注
	public void deleteMyFollow(int userId, int followedUserId) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			int rs=0;
			String sql="delete from follow where follow_user_id=? and followed_user_id=?";			
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, followedUserId);
			rs=pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/*
	 * 添加关注
	 * 根据questionId，userId添加collection_question中的记录
	 * */
	public Boolean insertFollow(int followUserId,int followedUserId){
		 
		Connection conn=null;
		int i=0;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="insert into follow(follow_user_id,followed_user_id) values(?,?)";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, followUserId);
			pre.setInt(2, followedUserId);
			i=pre.executeUpdate();
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

	
	/*
	 * 判断是否关注过
	 * */
	public boolean ifExistHuanZhu(int followUserId,int followedUserId) {
		// TODO Auto-generated method stub
				boolean b=false;
				int i=0;
				Connection conn=null;
				try {
					conn = DataBase.getConnection();
					PreparedStatement pre=null;
					ResultSet res=null;
					String sql="select * from follow where follow_user_id=? and followed_user_id=?";
					pre=conn.prepareStatement(sql);
					pre.setInt(1, followUserId);
					pre.setInt(2, followedUserId);
					res=pre.executeQuery();
					if(res.next()) {
						b= true;
					}else{
						b=false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return b;
			}
}
