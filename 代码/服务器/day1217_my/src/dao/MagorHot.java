package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MajorBean;
import servlet.DataBase;

public class MagorHot {
	
	//热门专业
	
		public List<MajorBean> getMajorList(){
			List<MajorBean> majorList=new ArrayList<MajorBean>();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select major_id,major_name,major_want,major_need,major.major_type_id,major_type_name,major_introduce,major_subject,major_work "
						+ "from major,major_type "
						+ "where major.major_type_id=major_type.major_type_id";
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("MajorDao:已经联合查询出专业");
					MajorBean major=new MajorBean();
					major.setMajorId(res.getInt("major_id"));
					major.setMajorName(res.getString("major_name"));
					major.setMajorWant(res.getInt("major_want"));
					System.out.println(major.getMajorWant());
					major.setMajorNeed(res.getInt("major_need"));
					major.setMajorTypeId(res.getInt("major_type_id"));
					major.setMajorTypeName(res.getString("major_type_name"));
					major.setMajorInf(res.getString("major_introduce"));
					major.setMajorSubject(res.getString("major_subject"));
					major.setMajorWork(res.getString("major_work"));
					majorList.add(major);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("majorDao:查询出的majorlist长度为"+majorList.size());

			return majorList;
		}
		
		//搜索专业
		public List<MajorBean> getMajorSubList(String majorName){
			List<MajorBean> majorList=new ArrayList<MajorBean>();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select major_id,major_name,major_want,major_need,major.major_type_id,major_type_name,major_introduce,major_subject,major_work\r\n" + 
						"from major,major_type\r\n" + 
						"where major.major_type_id=major_type.major_type_id and major_name like '%"+majorName+"%'";
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					MajorBean major = new MajorBean();
					System.out.print("MajorBean:已经查询出专业");
					major.setMajorId(res.getInt("major_id"));
					major.setMajorName(res.getString("major_name"));
					major.setMajorWant(res.getInt("major_want"));
					System.out.println(major.getMajorWant());
					major.setMajorNeed(res.getInt("major_need"));
					major.setMajorTypeId(res.getInt("major_type_id"));
					major.setMajorTypeName(res.getString("major_type_name"));
					major.setMajorInf(res.getString("major_introduce"));
					major.setMajorSubject(res.getString("major_subject"));
					major.setMajorWork(res.getString("major_work"));
					majorList.add(major);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return majorList;
			
		}
}
