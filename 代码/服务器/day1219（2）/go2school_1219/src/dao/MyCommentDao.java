package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AnswerBean;
import bean.Article;
import bean.CommentBean;
import servlet.DataBase;

public class MyCommentDao {
	public List<CommentBean> getComm(int userId){
		List<CommentBean> myCommentList=new ArrayList<CommentBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet rs=null;
			String sql="select comment_id,comment_content,comment_time,answer_id,article_id from comment where user_id="+userId;
//					+userId;//如何判断到底是谁发布的
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()) {
				CommentBean comment=new CommentBean();
				System.out.print("CommentDao:已经查询出comment");
				comment.setCommentId(rs.getInt(1));
				comment.setCommentContent(rs.getString(2));
				comment.setCommentTime(rs.getDate(3));
				AnswerBean answer =new AnswerDao().getAnswerById(rs.getInt(4));
				comment.setAnswer(answer);
				Article article =new ArticleDao().getArticleById(rs.getInt(5));
				comment.setArticle(article);
				myCommentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCommentList;

		}
}
