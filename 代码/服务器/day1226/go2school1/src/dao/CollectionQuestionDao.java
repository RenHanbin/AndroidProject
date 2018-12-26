package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AnswerBean;
import bean.CollectionQuestionBean;
import bean.QuestionBean;
 
import bean.UserBean;
import servlet.DataBase;
 

public class CollectionQuestionDao {
	/*
	 * 根据userId找到questionId，在collection_question表中
	 * */
	
	public List<CollectionQuestionBean> getQuestionIdListByUserId(int userId){
		List<CollectionQuestionBean> collectionQuestionList=new ArrayList<CollectionQuestionBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select question_id from collection_question where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionQuestionDao:已经查询出question_id123456");
				CollectionQuestionBean collectionQuestion=new CollectionQuestionBean();
				collectionQuestion.setQusetionId(res.getInt("question_id"));
				collectionQuestionList.add(collectionQuestion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CollectionQuestionDao:查询出的collectionQuestionList长度为"+collectionQuestionList.size());
		return collectionQuestionList;
	}
	
	
	/*
	 * 根据questionId找到answerId,answerTime,answerContent,userImg,userName列表，在answer表中
	 * */
	public List<AnswerBean> getAnswerListByquestionId(int questionId){
		List<AnswerBean> answerList=new ArrayList<AnswerBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select answer_id,answer_time,answer_content,user_id from answer where question_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, questionId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionQuestionDao:已经查询出answer_id123456");
				AnswerBean answer=new AnswerBean();
				answer.setAnswerId(res.getInt("answer_id"));
				answer.setAnswerTime(res.getString("answer_time"));
				answer.setAnswerContent(res.getString("answer_content"));
				answer.setUserId(res.getInt("user_id"));
				UserDao userDao=new UserDao();
				UserBean user=userDao.getUserByUserId(res.getInt("user_id"));
				answer.setUser(user);
				answerList.add(answer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CollectionQuestionDao:查询出的answerList长度为"+answerList.size());
		return answerList;
	}
	

	
	/*
	 * 根据questionId找到questionTitle和userId(指创建此问题的用户)，在question表中
	 * */
	public QuestionBean getQuestionByQuestionId(int questionId) {
		QuestionBean questionbean=new QuestionBean();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select question_id,question_title,user_id from question where question_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,questionId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionQuestionDao:已经查询出question");	
				questionbean.setQuestionId(questionId);
				questionbean.setQuestionTitle(res.getString("question_title"));
				UserDao userDao= new UserDao();
				UserBean user=userDao.getUserByUserId(res.getInt("user_id"));
				questionbean.setQuestionUser(user);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionbean;
	}	
	
	
	/*
	 * 根据questionId找到关注此问题的userId的个数，在collection_question表中
	 * */
	public int getUserCountByQuestionId(int questionId) {
		int userCount=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select count(*) from collection_question where question_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,questionId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionQuestionDao:已经查询出关注此问题的userId的个数");
				userCount=res.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userCount;
	}
	
	/*
	 * 根据questionId，userId删除collection_question中的记录
	 * */
	public Boolean deteleQuestionIdListByUserIdQuestionId(int userId,int questionId){
		List<CollectionQuestionBean> deteleQuestionIdList=new ArrayList<CollectionQuestionBean>();
		Connection conn=null;
		int i=0;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="delete from collection_question where question_id=? and user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, questionId);
			pre.setInt(2, userId);
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
	
	
	/*
	 * 根据questionId，userId添加collection_question中的记录
	 * */
	public Boolean insertQuestionIdListByUserIdQuestionId(int userId,int questionId){
		List<CollectionQuestionBean> insertQuestionIdList=new ArrayList<CollectionQuestionBean>();
		Connection conn=null;
		int i=0;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="insert into collection_question(user_id,question_id) values(?,?)";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, questionId);
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


	/*
	 * 判断此问题是否收藏过
	 * */
	public boolean ifExist(int userId, int questionId) {
		// TODO Auto-generated method stub
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select * from collection_question where user_id=? and question_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, questionId);
			res=pre.executeQuery();
			if(res.next()) {
				b= true;
			}else{
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	
}
