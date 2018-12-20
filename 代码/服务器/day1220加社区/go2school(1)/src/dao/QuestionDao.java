package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.QuestionBean;
import servlet.DataBase;

public class QuestionDao {
	/*
	 * 获取所有的问题
	 * */
	public List<QuestionBean> getAllQuestion(){
		 List<QuestionBean> questionList=new ArrayList<QuestionBean>();
		 Connection conn=null;
		 try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select question_id,question_title,question_describe from question";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				QuestionBean question=new QuestionBean();
				question.setQuestionId(res.getInt("question_id"));
				question.setQuestionTitle(res.getString("question_title"));
				question.setQuestionDiscribe(res.getString("question_describe"));
				questionList.add(question);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return questionList;
	}
	
	
	/*
	 * 添加一条问题question
	 * */
	public Boolean addQuestion(int questionId,String questionTitle,String questionDiscribe,int userId) {
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="insert into question(question_id,question_title,question_describe,question_time,user_id) values(?,?,?,?,?)";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,questionId);
			pre.setString(2,questionTitle);
			pre.setString(3, questionDiscribe);
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pre.setString(4, df.format(new Date()));
			pre.setInt(5, userId);
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
}
