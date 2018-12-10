package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CityBean;
import servlet.DataBase;

public class CityDao {
	public List<CityBean> getCityList(){
		List<CityBean>cityList=new ArrayList<CityBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select city_name"
					+ "from city,province"
					+"where province_id=city_id";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityDao:已经联合查询出专业");
				CityBean city=new CityBean();
				city.setCityId(res.getInt("city_id"));
				city.setCityName(res.getString("city_name"));
				cityList.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cityDao:查询出的citylist长度为"+cityList.size());
		return cityList;
	}

}
