package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.RegionalEconomyBean;
import servlet.DataBase;

public class RegionalEconomyDao {

	public List<RegionalEconomyBean> getRegionalEconomyList(){
		List<RegionalEconomyBean> regionaleconomyList=new ArrayList<RegionalEconomyBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			/*String sql="select city_name,city_gdp,count(school_id),city_salary,province_name,city_type_name,city_content "
					+ "from city,school,province,city_type "
					+"where city.city_id=school.city_id and city.province_id=province.province_id and city.city_type_id=city_type.city_type_id "
					+"group by school.city_id ";*/
			String sql="select city_name,city_gdp,count(school_id),city_salary,province_name,city_type_name,city_content,city_img "
					+ "from city,province,city_type,school "
					+"where city.city_id=school.city_id and city.province_id=province.province_id and city.city_type_id=city_type.city_type_id " 
					+"group by school.city_id ";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("RegionalEconomyDao:已经联合查询出专业");
				RegionalEconomyBean regionaleconomy=new RegionalEconomyBean();
				regionaleconomy.setCityGdp(res.getInt("city_gdp"));
				regionaleconomy.setCitySalary(res.getInt("city_salary"));
				regionaleconomy.setCityName(res.getString("city_name"));
				regionaleconomy.setCityType(res.getString("city_type_name"));
				regionaleconomy.setProvince(res.getString("province_name"));
				regionaleconomy.setCityContent(res.getString("city_content"));
				regionaleconomy.setCityImg(res.getString("city_img"));
				regionaleconomy.setCityTitle(res.getString("city_name"));
				regionaleconomy.setCollegeNum(res.getInt(3));
				regionaleconomyList.add(regionaleconomy);
			}
			System.out.print("province_name"+regionaleconomyList.get(0).getProvince());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("regionaleconomyDao:查询出的regionaleconomylist长度为"+regionaleconomyList.size());
		return regionaleconomyList;
	}
	public List<RegionalEconomyBean> getCityNameByName(String name){
		List<RegionalEconomyBean> cityList = new ArrayList<RegionalEconomyBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select city_name,city_gdp,count(school_id),city_salary,province_name,city_type_name,city_content,city_img "
				+ "from city,province,city_type,school "
				+"where city.city_id=school.city_id and city_name like \"%"+name+"%\" and city.province_id=province.province_id and city.city_type_id=city_type.city_type_id  "
				+"group by school.city_id ";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("RegionalEconomyDao");
				RegionalEconomyBean city = new RegionalEconomyBean();
				city.setCityGdp(res.getInt("city_gdp"));
				city.setCitySalary(res.getInt("city_salary"));
				city.setCityName(res.getString("city_name"));
				city.setCityType(res.getString("city_type_name"));
				city.setProvince(res.getString("province_name"));
				city.setCityContent(res.getString("city_content"));
				city.setCityImg(res.getString("city_img"));
				city.setCityTitle(res.getString("city_name"));
				city.setCollegeNum(res.getInt(3));
				cityList.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cityList;
	}
	
}
