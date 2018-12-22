package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Article;
import bean.Writer;
import bean.CollectionArticleBean;
import servlet.DataBase;

public class CollectionArticleDao {
	/*
	 * 根据userId找到articleId，在collection_article表中
	 * */
	public List<CollectionArticleBean> getArticleIdListByUserId(int userId){
		List<CollectionArticleBean> collectionArticleList=new ArrayList<CollectionArticleBean>();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select article_id from collection_article where user_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionArticleDao:已经查询出article_id123456");
				CollectionArticleBean collectionArticle=new CollectionArticleBean();
				collectionArticle.setArticleId(res.getInt("article_id"));
				collectionArticleList.add(collectionArticle);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CollectionArticleDao:查询出的collectionArticleList长度为"+collectionArticleList.size());
		return collectionArticleList;
	}
	
	
	/*
	 * 根据articleId找到articleTitle和articleImg，在article表中
	 * */
	public Article getArticleByArticleId(int articleId) {
		Article articleBean=new Article();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select article_id,article_title,article_content,article_img,article_time,writer_id from article where article_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,articleId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionArticleDao:已经查询出article");
				articleBean.setArticleId(articleId);
				articleBean.setArticleTitle(res.getString("article_title"));
				articleBean.setArticleContent(res.getString("article_content"));
				articleBean.setArticleImg(res.getString("article_img"));
				articleBean.setArticleTime(res.getString("article_time"));
				WriterDao writerDao=new WriterDao();
				Writer writer=writerDao.getWriterById(res.getInt("writer_id"));
				articleBean.setWriter(writer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articleBean;
	}
	
	
	/*根据articleId找到comment的个数，在commment表中
	 * */
	
	public int getCommentCountArticleId(int articleId) {
		int commentCount=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select count(*) from comment where article_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,articleId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("CollectionArticleDao:已经查询出comment");
				commentCount=res.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentCount;
	}


	//判断是否收藏过
	public boolean ifExist(int userId, int articleId) {
		// TODO Auto-generated method stub
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select * from collection_article where user_id=? and article_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, articleId);
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


	//取消收藏
	public boolean deleteCollectionMajor(int userId, int articleId) {
		// TODO Auto-generated method stub
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="delete from collection_article where user_Id=? and article_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, articleId);
			i=pre.executeUpdate();
			if(i>0) {
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


	//添加收藏
	public boolean addCollectionMajor(int userId, int articleId) {
		// TODO Auto-generated method stub
		boolean b=false;
		int i=0;
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="insert into collection_article(user_id,article_id) values(?,?)";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, articleId);
			i=pre.executeUpdate();
			if(i>0) {
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
