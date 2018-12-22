package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CityTypeBean;
import bean.PositionBean;
import bean.ProvinceBean;
import bean.ProvinceStudent;
import bean.StudentType;
import servlet.DataBase;

public class ProvinceDao {
	
	//根据Id查找对应的province
		public ProvinceBean getProvinceById(int provinceId) {
			ProvinceBean province=new ProvinceBean();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select province_id,province_name,position_id from province where province_id=?";
				pre=conn.prepareStatement(sql);
				pre.setInt(1, provinceId);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("provinceDao:已经查询出根据id找到province");
					province.setProvinceId(provinceId);
					province.setProvinceName(res.getString("province_name"));
					int positionId=res.getInt("position_id"); 
					PositionDao positionDao=new PositionDao();
					PositionBean position=positionDao.getPositionById(positionId);
					province.setPosition(position);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return province;
		}
		
	//根据name找对应province
		public ProvinceBean getProvinceByName(String name) {
			ProvinceBean province=new ProvinceBean();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select province_id,province_name,position_id from province where province_name like \""+name+"\" ";
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("provinceDao:已经查询出根据id找到province");
					province.setProvinceId(res.getInt("province_id"));
					province.setProvinceName(name);
					int positionId=res.getInt("position_id"); 
					PositionDao positionDao=new PositionDao();
					PositionBean position=positionDao.getPositionById(positionId);
					province.setPosition(position);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return province;
		}
	
	//查找所有province
	public List<ProvinceBean> getProvinceList(){
		List<ProvinceBean> provinceList=new ArrayList<ProvinceBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select *"
					+ "from province";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("ProvinceDao:已经联合查询出专业");
				ProvinceBean province=new ProvinceBean();
				province.setProvinceId(res.getInt("province_id"));
				province.setProvinceName(res.getString("province_name"));
				provinceList.add(province);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("provinceDao:查询出的provincelist长度为"+provinceList.size());
		return provinceList;
	}

	//查找根据学生类别和省份查分数线
	public ProvinceStudent getLine(String studentTypeName,String provinceName) {
		ProvinceStudent line=new ProvinceStudent();
		StudentTypeDao studentTypeDao=new StudentTypeDao();
		StudentType studentType=studentTypeDao.findStudentTypeByName(studentTypeName);
		ProvinceBean province=getProvinceByName(provinceName);
		int pId=province.getProvinceId();
		int spId=studentType.getStudentTypeId();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select * from province_studenttype where province_id=? and student_type_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, pId);
			pre.setInt(2, spId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("provinceDao:已经查询出根据id找到province");
				line.setProvinceId(pId);
				line.setStudentTypeId(spId);
				line.setFirstGradeLine(res.getInt("first_gradeline"));
				line.setSecondGradeLine(res.getInt("second_gradeline"));
				line.setThirdGradeLine(res.getInt("third_gradeline"));
				line.setOtherGradeLine(res.getInt("other_gradeline"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
}
