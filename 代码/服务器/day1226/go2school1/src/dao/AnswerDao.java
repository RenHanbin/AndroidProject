package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bean.AnswerBean;
import servlet.DataBase;

public class AnswerDao {

	//添加回答
	public boolean addAnswer(int userId, int questionId, String answerContent) {
		// TODO Auto-generated method stub
		int i=0;
		Connection conn=null;
		try {
			conn=DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="insert into answer(user_id,answer_content,question_id,answer_time) values (?,?,?,?) ";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setString(2, answerContent);
			pre.setInt(3, questionId);
			Date day=new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String time=df.format(day);
			System.out.println(time); 
			pre.setString(4, time);
			i=pre.executeUpdate();
			if(i>0) {
				return true;
			}else {
				return false;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public int getAnswerId(int userId, int questionId) throws SQLException {
		int answerId = 0;
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select answer_id from answer where user_id=? and question_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, questionId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				answerId=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answerId;
	}
	
	//根据ID查找对应answer对象
	public AnswerBean getAnswerById(int answerId) throws SQLException {
		AnswerBean answer = new AnswerBean();
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from answer,user where answer_id=? and answer.user_id=user.user_id";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answerId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				answer.setAnswerId(rs.getInt("answer_id"));
				answer.setAnswerContent(rs.getString("answer_content"));
				answer.setAnswerTime(rs.getDate("answer_time").toString());
				answer.setQuestionId(rs.getInt("question_id"));
				answer.setUserId(rs.getInt("user_id"));
				answer.setAnswerUserName(rs.getString("user_name"));
				answer.setAnswerUserImg(rs.getString("user_img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

}
