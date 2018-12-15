package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import servlet.DataBase;

public class CollectionSchoolDao {

	//添加收藏学校
	public boolean addCollectionSchool(int userId,int schoolId) {
		// TODO Auto-generated method stub
		
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="insert into collection_school(user_id,school_id) values(?,?)";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, schoolId);
			i=pre.executeUpdate();
			if(i>0) {
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

	//取消收藏学校
	public boolean deleteCollectionSchool(int userId, int schoolId) {
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="delete from collection_school where user_Id=? and school_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, schoolId);
			i=pre.executeUpdate();
			if(i>0) {
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
