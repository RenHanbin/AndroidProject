package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bean.PositionBean;
import bean.ProvinceBean;
import servlet.DataBase;

public class PositionDao {
	
	//根据Id查找对应的position
			public PositionBean getPositionById(int positionId) {
				PositionBean position=new PositionBean();
				Connection conn=null;
				try {
					conn = DataBase.getConnection();
					PreparedStatement pre=null;
					ResultSet res=null;
					String sql="select position_id,position_name from position where position_id=?";
					pre=conn.prepareStatement(sql);
					pre.setInt(1, positionId);
					res=pre.executeQuery();
					while(res.next()) {
						System.out.print("positionDao:已经查询出根据作者id找到作者position");
						position.setPositionId(positionId);
						position.setPositionName(res.getString("position_name"));
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return position;
			}
	
	public List<PositionBean> getPositionList(){
		List<PositionBean> positionList=new ArrayList<PositionBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select *"
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
