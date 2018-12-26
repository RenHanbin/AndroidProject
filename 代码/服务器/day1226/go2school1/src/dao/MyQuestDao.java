package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CommentBean;
import bean.QuestionBean;
import bean.UserBean;
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
				attenNum=rs.getInt("count(*)");
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
				commNum=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commNum;
	}

	public List<QuestionBean> getAllQuestList() {
		List<QuestionBean> allQuestionList = new ArrayList<QuestionBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select question.question_id,question_title,question_img \r\n" + 
						"from question,collection_question\r\n" + 
						"where question.question_id=collection_question.question_id\r\n" + 
						"GROUP BY collection_question.question_id\r\n" + 
						"ORDER BY COUNT(collection_question.user_id) DESC";
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()) {
				QuestionBean question=new QuestionBean();
				question.setQuestionId(rs.getInt(1));
				question.setQuestionTitle(rs.getString(2));
				question.setQuestionImg(rs.getString(3));
				allQuestionList.add(question);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allQuestionList;
	}
	
	/*
	 * 根据followedUserId找到用户所关注人发表的question，在question表中
	 * */
	public List<QuestionBean> getQuestionByFollowedUserId(int followedUserId) {
		List<QuestionBean> questionList = new ArrayList<QuestionBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select question_id,question_title,question_describe,question_time,user_id from question where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,followedUserId);
			res=pre.executeQuery();
			System.out.println("1234567890");
			while(res.next()) {
				System.out.println("12345678901234567890");
				QuestionBean questionbean=new QuestionBean();
				questionbean.setQuestionId(res.getInt("question_id"));
				questionbean.setQuestionTitle(res.getString("question_title"));
				questionbean.setQuestionTime(res.getDate("question_time"));
				questionbean.setQuestionDiscribe(res.getString("question_describe"));
				UserBean user=new MyUserDao().getUser(res.getInt("user_id"));
				questionbean.setQuestionUser(user);
				questionList.add(questionbean);
				System.out.println("已经通过followeduserid查出questionlist"+questionList.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionList;
	}	


}
