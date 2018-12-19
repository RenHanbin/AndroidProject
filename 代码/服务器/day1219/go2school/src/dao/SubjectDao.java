package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.MajorTypeBean;
import bean.Subject;
import servlet.DataBase;

public class SubjectDao {

	/**
	 * 根据课程名找到id
	 */
	public int getSubId(String name) {
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select subject_id "
				+"from subject "
				+"where subject_name=\""+name+"\"";
		Subject subject =new Subject();
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SubjectDao");	
				subject.setSubjectId(res.getInt("subject_id"));
				subject.setSubjectName(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(subject.getSubjectId());
		return subject.getSubjectId();
	}
	/**
	 * 根据课程id找到专业类别id
	 */
	public int getTypeId(int subId) {
		Connection conn= null;
		PreparedStatement pre=null;
		ResultSet res=null;
		String sql="select major_type_id "
				+"from subject_majortype "
				+"where subject_id=\""+subId+"\"";
		MajorTypeBean type = new MajorTypeBean();
		try {
			conn= DataBase.getConnection();
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("SubjectDao");	
				type.setMajorTypeId(res.getInt("major_type_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(type.getMajorTypeId());
		return type.getMajorTypeId();
	}
}
