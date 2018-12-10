package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MajorBean;
import bean.SchoolBean;
import servlet.DataBase;

public class SchoolDao {
	/**
	 * 大学排名
	 */
	public List<SchoolBean> getSchoolPaiMing(){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select school_name, city_name "
				+ "from school,city "
				+ "where school.city_id=city.city_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");
				SchoolBean school = new SchoolBean();
				school.setSchoolName(res.getString("school_name"));
				school.setCityName(res.getString("city_name"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	/**
	 * 根据名称搜索大学
	 */
	public List<SchoolBean> getSchoolPaiMingByName(String name){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select school_name, city_name "
				+ "from school,city "
				+ "where school.city_id=city.city_id and school_name like \"%"+name+"%\" "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");
				SchoolBean school = new SchoolBean();
				school.setSchoolName(res.getString("school_name"));
				school.setCityName(res.getString("city_name"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
}
