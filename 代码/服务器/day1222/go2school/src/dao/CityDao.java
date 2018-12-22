package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CityBean;
import bean.CityTypeBean;
import bean.ProvinceBean;
import servlet.DataBase;

public class CityDao {
	public List<CityBean> getCityList(){
		List<CityBean>cityList=new ArrayList<CityBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select city.city_name "
					+ "from city,province "
					+ "where city.province_id=province.province_id";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityDao:已经联合查询出专业");
				CityBean city=new CityBean();
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
	
	
	//获得cityjobList
	public List<CityBean> getCityJobList(){
		List<CityBean> cityJobList=new ArrayList<CityBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select * from city";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityDao:已经联合查询出city");
				CityBean city=new CityBean();
				city.setCityId(res.getInt("city_id"));
				city.setCityName(res.getString("city_name"));
				city.setCityContent(res.getString("city_content"));
				city.setCitySalary(res.getDouble("city_salary"));
				city.setCityGdp(res.getDouble("city_gdp"));
				city.setCityImg(res.getString("city_img"));
				int cityTypeId=res.getInt("city_type_id"); 
				int provinceId=res.getInt("province_id"); 
				CityTypeDao cityTypeDao=new CityTypeDao();
				CityTypeBean cityType=cityTypeDao.getCityTypeById(cityTypeId);
				city.setCityType(cityType);
				ProvinceDao provinceDao=new ProvinceDao();
				ProvinceBean province=provinceDao.getProvinceById(provinceId);
				city.setProvince(province);
				cityJobList.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cityDao:查询出的cityJoblist长度为"+cityJobList.size());
		return cityJobList;
	}

	//根据城市名搜索城市
	public List<CityBean> getSearchCityList(String cityName){
		List<CityBean> cityJobList=new ArrayList<CityBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select * from city where city_name=?";
			pre=conn.prepareStatement(sql);
			pre.setString(1, cityName);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityDao:已经联合查询出city");
				CityBean city=new CityBean();
				city.setCityId(res.getInt("city_id"));
				city.setCityName(res.getString("city_name"));
				city.setCitySalary(res.getDouble("city_salary"));
				city.setCityGdp(res.getDouble("city_gdp"));
				city.setCityContent(res.getString("city_content"));
				city.setCityImg(res.getString("city_img"));
				int cityTypeId=res.getInt("city_type_id"); 
				int provinceId=res.getInt("province_id"); 
				CityTypeDao cityTypeDao=new CityTypeDao();
				CityTypeBean cityType=cityTypeDao.getCityTypeById(cityTypeId);
				city.setCityType(cityType);
				ProvinceDao provinceDao=new ProvinceDao();
				ProvinceBean province=provinceDao.getProvinceById(provinceId);
				city.setProvince(province);
				cityJobList.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cityDao:查询出的cityJoblist长度为"+cityJobList.size());
		return cityJobList;
	}

	//根据城市类别搜索城市
	public List<CityBean> getCityListByType(String cityTypeName){
		List<CityBean> cityJobList=new ArrayList<CityBean>();
		int cityTypeId;
		CityTypeDao cityTypeDao=new CityTypeDao();
		cityTypeId=cityTypeDao.getCityTypeIdByName(cityTypeName);
		System.out.println("查找出来的citytypeId="+cityTypeId);
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select * from city where city_type_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, cityTypeId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityDao:已经联合查询出city");
				CityBean city=new CityBean();
				city.setCityId(res.getInt("city_id"));
				city.setCityName(res.getString("city_name"));
				city.setCitySalary(res.getDouble("city_salary"));
				city.setCityGdp(res.getDouble("city_gdp")); 
				city.setCityContent(res.getString("city_content"));
				city.setCityImg(res.getString("city_img"));
				int provinceId=res.getInt("province_id"); 
				cityTypeDao=new CityTypeDao();
				CityTypeBean cityType=cityTypeDao.getCityTypeById(cityTypeId);
				city.setCityType(cityType);
				ProvinceDao provinceDao=new ProvinceDao();
				ProvinceBean province=provinceDao.getProvinceById(provinceId);
				city.setProvince(province);
				cityJobList.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cityDao:查询出的cityJoblist长度为"+cityJobList.size());
		return cityJobList;
	}

	/*
	 * 根据城市查找学校
	 */
	public List<CityBean> getSchoolNameByCity(String name){
		List<CityBean> schoolList = new ArrayList<CityBean>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select school_name,school_rank,school_best_major "
				+ "from school,city "
				+ "where school.city_id=city.city_id and city.city_id=\""+name+"\" " ;
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityDao");
				CityBean school = new CityBean();
				school.setSchoolName(res.getString("school_name"));
				school.setSchoolRank(res.getInt("school_rank"));
				school.setSchoolBestMajor(res.getString("school_best_major"));
				schoolList.add(school);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolList;
	}
	
	public int getId(String cityName) {
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select city_id "
				+"from city "
				+"where city_name=\""+cityName+"\"";
		CityBean city = new CityBean();
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CityDao");				
				city.setCityId(res.getInt("city_id"));
				city.setCityName(cityName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(city.getCityId());
		return city.getCityId();
	}
	
}
