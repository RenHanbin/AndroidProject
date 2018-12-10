package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bean.PositionBean;
import servlet.DataBase;

public class PositionDao {
	public List<PositionBean> getPositionList(){
		List<PositionBean> positionList=new ArrayList<PositionBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select position_id,position_name"
					+ "from position";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("PositionDao:已经联合查询出专业");
				PositionBean position=new PositionBean();
				position.setPositionId(res.getInt("position_id"));
				position.setPositionName(res.getString("position_name"));
				positionList.add(position);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("positionDao:查询出的positionlist长度为"+positionList.size());
		return positionList;
	}


}
