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
				UserBean user =new MyUserDao().getUser(rs.getInt(2));
				follow.setFollowedUser(user);
				myFollowList.add(follow);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myFollowList;

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
}
