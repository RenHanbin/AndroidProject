package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CollectionMajorBean;
import bean.MajorBean;
import servlet.DataBase;

public class CollectionMajorDao {
	
	/*
	 * 根据userId找到majorId
	 * */
	public List<CollectionMajorBean> getMajorListByUserId(int userId){
		List<CollectionMajorBean> majorIdListByUserId=new ArrayList<CollectionMajorBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select major_id from collection_major where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionMajorDao:已经查询出major_id1234567890");
				CollectionMajorBean collectionMajor=new CollectionMajorBean();
				collectionMajor.setMajorId(res.getInt("major_id"));
				majorIdListByUserId.add(collectionMajor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CollectionMajorDao:查询出的majorIdListByUserId长度为"+majorIdListByUserId.size());
		return majorIdListByUserId;
	}
	
	/*
	 * 根据majorId找到majorName和majorTypeId
	 * */
	public MajorBean getMajorListByMajorId(int majorId){
		MajorBean major=new MajorBean();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select major_name,major_type_name from major,major_type where major.major_type_id=major_type.major_type_id and major_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, majorId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionMajorDao:已经查询出major");	
				major.setMajorName(res.getString("major_name"));
				major.setMajorTypeName(res.getString("major_type_name"));
				major.setMajorId(majorId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return major;
	}
	/*
	 * 根据majorId，userId删除collection_major中的记录
	 * */
	public List<CollectionMajorBean> deteleMajorIdListByUserIdMajorId(int userId,int majorId){
		List<CollectionMajorBean> deteleMajorIdList=new ArrayList<CollectionMajorBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="delete from collection_major where major_id=? and user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, majorId);
			pre.setInt(2, userId);
			pre.executeUpdate();
			res=pre.getGeneratedKeys();
			CollectionMajorDao collectionMajorDao=new CollectionMajorDao();
			deteleMajorIdList=collectionMajorDao.getMajorListByUserId(userId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deteleMajorIdList;
	}

	/*
	 * 删除收藏专业
	 * */
	public boolean deleteCollectionMajor(int userId,int majorId) {
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="delete from collection_major where user_Id=? and major_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, majorId);
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
	 * 添加收藏专业
	 * */
	public boolean addCollectionMajor(int userId,int majorId) {
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="insert into collection_major(user_Id,major_id) values(?,?)";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, majorId);
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
