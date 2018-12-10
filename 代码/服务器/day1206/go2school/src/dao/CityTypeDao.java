package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CityTypeBean;
import servlet.DataBase;

public class CityTypeDao {
	public List<CityTypeBean> getCityTypeList(){
		List<CityTypeBean>citytypeList=new ArrayList<CityTypeBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select city_type_name,city_type_id"
					+ "from citytype";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityTypeDao:已经联合查询出专业");
				CityTypeBean citytype=new CityTypeBean();
				citytype.setCitytypeId(res.getInt("city_type_id"));
				citytype.setCitytypeName(res.getString("city_type_name"));
				citytypeList.add(citytype);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("citytypeDao:查询出的citytypelist长度为"+citytypeList.size());
		return citytypeList;
	}
}
