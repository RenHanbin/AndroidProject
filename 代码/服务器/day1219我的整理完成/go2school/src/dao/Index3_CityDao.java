package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Index3_CityBean;
import servlet.DataBase;

public class Index3_CityDao {

	public List<Index3_CityBean> getIndex3_CityList(){
		List<Index3_CityBean> index3cityList=new ArrayList<Index3_CityBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			
			String sql="select city_name,city_gdp,count(school_id),city_salary,province_name,city_type_name,city_content,city_img "
					+ "from city,province,city_type,school "
					+"where city.city_id=school.city_id and city.province_id=province.province_id and city.city_type_id=city_type.city_type_id " 
					+"group by school.city_id ";

			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("Index3_CityDao:已经联合查询出城市");
				Index3_CityBean index3city=new Index3_CityBean();
				index3city.setCityGdp(res.getInt("city_gdp"));
				index3city.setCitySalary(res.getInt("city_salary"));
				index3city.setCityName(res.getString("city_name"));
				index3city.setCityType(res.getString("city_type_name"));
				//index3city.setProvince(res.getString("province_name"));
				index3city.setCityContent(res.getString("city_content"));
				index3city.setCityImg(res.getString("city_img"));
				index3city.setCityTitle(res.getString("city_name"));
				index3cityList.add(index3city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Index3_CityListDao:查询出的index3citylist长度为"+index3cityList.size());
		return index3cityList;
	}
}
