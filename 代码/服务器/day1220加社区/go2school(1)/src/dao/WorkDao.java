package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.SchoolBean;
import bean.Work;
import servlet.DataBase;

public class WorkDao {
	/**
	 * 工作类型
	 */
	public List<Work> getWorkList(){
		List<Work> workList = new ArrayList<Work>();
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select work_id, work_name "
				+ "from work ";
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("WorkDao");
				Work work = new Work();
				work.setWorkId(res.getInt("work_id"));
				work.setWorkName(res.getString("work_name"));
				workList.add(work);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workList;
	}
}
