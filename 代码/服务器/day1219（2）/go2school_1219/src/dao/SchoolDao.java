package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MajorBean;
import bean.Rank;
import bean.SchoolBean;
import bean.SchoolTypeBean;
import bean.Work;
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
		String sql="select * "
				+ "from school,city,school_type "
				+ "where school.city_id=city.city_id and school_type.school_type_id=school.school_type_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
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
		String sql="select * "
				+ "from school,city,school_type "
				+ "where school.city_id=city.city_id and school_name like \"%"+name+"%\" and school_type.school_type_id=school.school_type_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
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
	 * 就业方向
	 */
	public List<SchoolBean> getSchoolWorkList(){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select * "
				+ "from school,city,work_school,school_type "
				+ "where school.city_id=city.city_id and school.school_id=work_school.school_id and school_type.school_type_id=school.school_type_id "
				+ "order by work_school.salary";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				school.setCityName(res.getString("city_name"));
				school.setSalary(res.getString("salary"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	
	/**
	 * 根据名字找到Id
	 */
	public int getId(String workName) {
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select work_id "
				+"from work "
				+"where work_name=\""+workName+"\"";
		Work work = new Work();
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");				
				work.setWorkId(res.getInt("work_id"));
				work.setWorkName(workName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(work.getWorkId());
		return work.getWorkId();
	}
	/**
	 * 根据work选择学校
	 */
	public List<SchoolBean> getSchoolWorkListByWork(String workId){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select * "
				+ "from school,city,work_school,school_type "
				+ "where school.city_id=city.city_id and school.school_id=work_school.school_id and work_id="+workId+" and school_type.school_type_id=school.school_type_id "
				+ "order by work_school.salary DESC";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				school.setCityName(res.getString("city_name"));
				school.setSalary(res.getString("salary"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	
	/**
	 * 学校代码
	 */
	public List<SchoolBean> getSchoolNum(){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select * "
				+ "from school,school_type "
				+ "where school_type.school_type_id=school.school_type_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	
	/**
	 * 学校类别筛选
	 */
	public List<SchoolBean> getSchoolNumById(int id){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select * "
				+ "from school,school_type "
				+ "where school_type.school_type_id=\""+id+"\" and school_type.school_type_id=school.school_type_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	
	/**
	 * 根据类别名字找到类别id
	 * @param typeName
	 * @return
	 */
	public int getSchoolTypeId(String typeName) {
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select school_type_id "
				+"from school_type "
				+"where school_type_name=\""+typeName+"\"";
		SchoolTypeBean type = new SchoolTypeBean();
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SchoolDao");		
				type.setSchoolTypeId(res.getInt("school_type_id"));
				type.setSchoolTypeName(typeName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type.getSchoolTypeId();
	}
	
	/**
	 * 所有学校招生信息
	 */
	public List<SchoolBean> getSchoolAll(){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select * "
				+ "from school,need_student,school_type "
				+ "where school.school_id = need_student.school_id and school_type.school_type_id=school.school_type_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				school.setNeed(res.getString("need_student"));
				school.setLow_grade(res.getString("low_grade"));
				school.setLow_rank(res.getInt("low_rank"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	
	/**
	 * 名次
	 */
	public String getRank(int score,String year,String province){
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		Rank rank = new Rank();
		String sql="select ranking "
				+ "from rank,time,province "
				+ "where grade=\""+score+"\" and province_name=\""+province+"\" and time_name=\""+year+"\" and time.time_id=rank.time_id and rank.province_id=province.province_id";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				rank.setRanking(res.getString("ranking"));
				rank.setGrade(score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rank.getRanking()+"";
	}
	/**
	 * 根据排名显示学校
	 */
	//冲一冲
	public List<SchoolBean> getSchool(int paiming){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select * "
				+ "from school,need_student,school_type "
				+ "where school.school_id = need_student.school_id and low_rank <=\""+paiming+"\" and "+paiming+"-low_rank<1000 and school_type.school_type_id=school.school_type_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				school.setNeed(res.getString("need_student"));
				school.setLow_grade(res.getString("low_grade"));
				school.setLow_rank(res.getInt("low_rank"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	//稳一稳
	public List<SchoolBean> getSchool2(int paiming){
		List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select * "
				+ "from school,need_student,school_type "
				+ "where school.school_id = need_student.school_id and low_rank >\""+paiming+"\" and low_rank-"+paiming+"<10000 and low_rank-"+paiming+">=1000 and school_type.school_type_id=school.school_type_id "
				+ "order by school_rank";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				SchoolBean school = new SchoolBean();
				school.setSchoolId(res.getInt("school_id"));
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setCityId(res.getInt("city_id"));
				school.setSchoolImg(res.getString("school_img"));
				school.setSchoolContent(res.getString("school_content"));
				school.setSchoolNum(res.getString("school_number"));
				school.setSchoolTypeId(res.getInt("school_type_id"));
				school.setSchoolTypeName(res.getString("school_type_name"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				school.setNeed(res.getString("need_student"));
				school.setLow_grade(res.getString("low_grade"));
				school.setLow_rank(res.getInt("low_rank"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	//保底
		public List<SchoolBean> getSchool3(int paiming){
			List<SchoolBean> schoolList = new ArrayList<SchoolBean>();
			Connection conn= null;
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select * "
					+ "from school,need_student,school_type "
					+ "where school.school_id = need_student.school_id and low_rank >\""+paiming+"\" and low_rank-"+paiming+">=10000 and school_type.school_type_id=school.school_type_id "
					+ "order by school_rank";
			try {
				conn= DataBase.getConnection();
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					SchoolBean school = new SchoolBean();
					school.setSchoolId(res.getInt("school_id"));
					school.setSchoolName(res.getString("school_name"));
					school.setSchoolRank(res.getInt("school_rank"));
					school.setCityId(res.getInt("city_id"));
					school.setSchoolImg(res.getString("school_img"));
					school.setSchoolContent(res.getString("school_content"));
					school.setSchoolNum(res.getString("school_number"));
					school.setSchoolTypeId(res.getInt("school_type_id"));
					school.setSchoolTypeName(res.getString("school_type_name"));
					school.setSchoolBestMajor(res.getString("school_best_major"));
					school.setNeed(res.getString("need_student"));
					school.setLow_grade(res.getString("low_grade"));
					school.setLow_rank(res.getInt("low_rank"));
					schoolList.add(school);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return schoolList;
		}

	

}
