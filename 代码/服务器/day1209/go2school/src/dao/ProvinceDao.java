package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProvinceBean;
import servlet.DataBase;

public class ProvinceDao {
	public List<ProvinceBean> getProvinceList(){
		List<ProvinceBean> provinceList=new ArrayList<ProvinceBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select province_id,province_name"
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
}
