package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CollectionSchoolBean;
import bean.SchoolBean;
import servlet.DataBase;

public class CollectionSchoolDao {
	
	//判断此学校是否收藏过
		public boolean ifExist(int userId, int schoolId) {
			// TODO Auto-generated method stub
			boolean b=false;
			int i=0;
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select * from collection_school where user_id=? and school_id=?";
				pre=conn.prepareStatement(sql);
				pre.setInt(1, userId);
				pre.setInt(2, schoolId);
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
	
	/*
	 * 根据userId找到schoolId
	 * */
	public List<CollectionSchoolBean> getSchoolIdListByUserId(int userId){
		List<CollectionSchoolBean> collectionSchoolList=new ArrayList<CollectionSchoolBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select school_id from collection_school where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionSchoolDao:已经查询出school_id123456");
				CollectionSchoolBean collectionSchool=new CollectionSchoolBean();
				collectionSchool.setSchoolId(res.getInt("school_id"));
				collectionSchoolList.add(collectionSchool);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CollectionSchoolDao:查询出的collectionSchoolList长度为"+collectionSchoolList.size());
		return collectionSchoolList;
	}
	
	
	/*
	 * 根据schoolId找到schoolName、schoolRank、schoolImg和schoolTypeName
	 * */
	public SchoolBean getSchoolListBySchoolId(int schoolId) {
		SchoolBean schoolbean=new SchoolBean();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select school_id,school_name,school_rank,school_type_name,school_img,school_content,school_number,school_best_major from school,school_type where school.school_type_id=school_type.school_type_id and school_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,schoolId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionSchoolDao:已经查询出school");	
				schoolbean.setSchoolId(res.getInt("school_id"));
				schoolbean.setSchoolName(res.getString("school_name"));
				schoolbean.setSchoolRank(res.getInt("school_rank"));
				schoolbean.setSchoolTypeName(res.getString("school_type_name"));
				schoolbean.setSchoolImg(res.getString("school_img"));
				schoolbean.setSchoolContent(res.getString("school_content"));
				schoolbean.setSchoolNum(res.getString("school_number"));
				schoolbean.setSchoolBestMajor(res.getString("school_best_major"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolbean;
	}
	
	/*
	 * 根据schoolId，userId删除collection_school中的记录
	 * */
	public List<CollectionSchoolBean> deteleSchoolIdListByUserIdSchoolId(int userId,int schoolId){
		List<CollectionSchoolBean> deteleSchoolIdList=new ArrayList<CollectionSchoolBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="delete from collection_school where school_id=? and user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, schoolId);
			pre.setInt(2, userId);
			pre.executeUpdate();
			res=pre.getGeneratedKeys();
			CollectionSchoolDao collectionSchoolDao=new CollectionSchoolDao();
			deteleSchoolIdList=collectionSchoolDao.getSchoolIdListByUserId(userId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deteleSchoolIdList;
	}

}
