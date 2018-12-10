package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MajorBean;
import bean.UserBean;
import servlet.DataBase;

public class MajorDao {
	
	/**
	 * 就业前景
	 * @return
	 */
	
	public List<MajorBean> getMajorList(){
		List<MajorBean> majorList=new ArrayList<MajorBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select major_id,major_name,major_workPercent,major_salary,major.major_type_id,major_type_name "
					+ "from major,major_type "
					+ "where major.major_type_id=major_type.major_type_id";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("MajorDao:已经联合查询出专业");
				MajorBean major=new MajorBean();
				major.setMajorId(res.getInt("major_id"));
				major.setMajorName(res.getString("major_name"));
				major.setMajorWorkPercent(res.getDouble("major_workPercent"));
				major.setMajorSalary(res.getInt("major_salary"));
				major.setMajorTypeId(res.getInt("major_type_id"));
				major.setMajorTypeName(res.getString("major_type_name"));
				majorList.add(major);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return majorList;
	}

	/**
	 * 考研率
	 * @return
	 */
	public List<MajorBean> getMajorLearnList(){
		List<MajorBean> majorLearnList=new ArrayList<MajorBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select major_id,major_name,major_workPercent,major_study,major_go "
				+ "from major "
				+ "order by major_study DESC";
		try {	
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("MajorDao:已经联合查询出专业");
				MajorBean major=new MajorBean();
				major.setMajorId(res.getInt("major_id"));
				major.setMajorName(res.getString("major_name"));
				major.setMajorWorkPercent(res.getDouble("major_workPercent"));
				major.setMajorStudy(res.getDouble("major_study"));
				major.setMajorGo(res.getDouble("major_go"));
				majorLearnList.add(major);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return majorLearnList;
		
	}

	/**
	 * 出国
	 */
	public List<MajorBean> getMajorOutList(){
		List<MajorBean> majorLearnList=new ArrayList<MajorBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select major_id,major_name,major_workPercent,major_study,major_go "
				+ "from major "
				+ "order by major_go DESC";
		try {	
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("MajorDao:已经联合查询出专业");
				MajorBean major=new MajorBean();
				major.setMajorId(res.getInt("major_id"));
				major.setMajorName(res.getString("major_name"));
				major.setMajorWorkPercent(res.getDouble("major_workPercent"));
				major.setMajorStudy(res.getDouble("major_study"));
				major.setMajorGo(res.getDouble("major_go"));
				majorLearnList.add(major);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return majorLearnList;
		
	}
	
	/**
	 * 就业
	 */
	public List<MajorBean> getMajorWorkList(){
		List<MajorBean> majorLearnList=new ArrayList<MajorBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select major_id,major_name,major_workPercent,major_study,major_go "
				+ "from major "
				+ "order by major_workPercent DESC";
		try {	
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("MajorDao:已经联合查询出专业");
				MajorBean major=new MajorBean();
				major.setMajorId(res.getInt("major_id"));
				major.setMajorName(res.getString("major_name"));
				major.setMajorWorkPercent(res.getDouble("major_workPercent"));
				major.setMajorStudy(res.getDouble("major_study"));
				major.setMajorGo(res.getDouble("major_go"));
				majorLearnList.add(major);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return majorLearnList;
		
	}
}
