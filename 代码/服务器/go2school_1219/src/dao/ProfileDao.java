package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProfileBean;
import servlet.DataBase;

public class ProfileDao {

	public List<ProfileBean> getProfileList(){
		List<ProfileBean> profileList=new ArrayList<ProfileBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select school_name,school_rank,school_best_major "
					+ "from school ";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("ProfileDao:已经联合查询出学校");
				ProfileBean profile=new ProfileBean();
				profile.setSchoolName(res.getString("school_name"));
				profile.setSchoolRank(res.getInt("school_rank"));
				profile.setSchoolBestMajor(res.getString("school_best_major"));
				profileList.add(profile);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("profileDao:查询出的profilelist长度为"+profileList.size());
		return profileList;
	}
}
