package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.StudentType;
import servlet.DataBase;

public class StudentTypeDao {

	//根据name找对应元祖
	public StudentType findStudentTypeByName(String name) {
		StudentType studentType=new StudentType();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select student__type_id,student_type_name from student_type where student_type_name=?";
			pre=conn.prepareStatement(sql);
			pre.setString(1, name);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("StudentTypeDao:已经查询出根据作者id找到对应元祖");
				studentType.setStudentTypeId(res.getInt("student__type_id"));
				studentType.setStudentTypeName(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentType;
	}
	
}
