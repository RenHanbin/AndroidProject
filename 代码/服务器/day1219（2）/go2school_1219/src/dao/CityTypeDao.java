package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CityTypeBean;
import bean.Writer;
import servlet.DataBase;

public class CityTypeDao {
	
	//根据citytypeId查找对应的citytypeBean
	public CityTypeBean getCityTypeById(int cityTypeId) {
		CityTypeBean cityType=new CityTypeBean();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select city_type_id,city_type_name from city_type where city_type_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, cityTypeId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityTypeDao:已经查询出根据作者id找到作者cityType");
				cityType.setCityTypeId(cityTypeId);
				cityType.setCityTypeName(res.getString("city_type_name"));
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cityType;
	}
	
	//根据cityTypeName查找对应id
	public int getCityTypeIdByName(String cityTypeName) {
		int cityTypeId=1;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select city_type_id from city_type where city_type_name=?";
			pre=conn.prepareStatement(sql);
			pre.setString(1, cityTypeName);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityTypeDao:已经查询出根据name找到cityTypeId");
				cityTypeId=res.getInt("city_type_id");
				return cityTypeId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
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
				citytype.setCityTypeId(res.getInt("city_type_id"));
				citytype.setCityTypeName(res.getString("city_type_name"));
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
