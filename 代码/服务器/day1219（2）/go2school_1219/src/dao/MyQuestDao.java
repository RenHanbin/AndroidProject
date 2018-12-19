package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CommentBean;
import bean.QuestionBean;
import servlet.DataBase;

public class MyQuestDao{
	public List<QuestionBean> getQuestList(int userId){
	List<QuestionBean> myQuestionList = new ArrayList<QuestionBean>();
	Connection conn=null;
	try {
		conn = DataBase.getConnection();
		PreparedStatement pre=null;
		ResultSet rs=null;
		String sql="select question_id,question_title,question_describe,question_time from question where user_id="+userId;//如何判断到底是谁发布的
		pre=conn.prepareStatement(sql);
		rs=pre.executeQuery();
		while(rs.next()) {
			QuestionBean question=new QuestionBean();
			System.out.print("QuestionDao:已经查询出question");
			question.setQuestionId(rs.getInt(1));
			question.setQuestionTitle(rs.getString(2));
			question.setQuestionDiscribe(rs.getString(3));
			question.setQuestionTime(rs.getDate(4));
			myQuestionList.add(question);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return myQuestionList;

	}

	//获取关注人数
	public int getAttenNum(int questionId) {
		// TODO Auto-generated method stub
		int attenNum=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select count(*) from collection_question where question_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, questionId);
			rs=pre.executeQuery();
			while(rs.next()) {
				attenNum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attenNum;
	}

	//获取评论人数
	public int getCommNum(int userId) {
		// TODO Auto-generated method stub
		int commNum=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select count(*) from comment where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			rs=pre.executeQuery();
			while(rs.next()) {
				commNum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commNum;
	}

}
