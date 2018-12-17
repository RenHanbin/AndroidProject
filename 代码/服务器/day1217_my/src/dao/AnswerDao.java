package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AnswerBean;
import servlet.DataBase;

public class AnswerDao {

	public AnswerBean getAnswerById(int answerId) throws SQLException {
		AnswerBean answer = new AnswerBean();
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from answer where answer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answerId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				answer.setAnswerId(rs.getInt(1));
				answer.setAnswerContent(rs.getString(2));
				answer.setAnswerTime(rs.getDate(3));
				answer.setQuestionId(rs.getInt(4));
				answer.setUserId(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
}
