package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Article;
import bean.MajorBean;
import servlet.DataBase;

public class ArticleDao {

	//获得所有“高考新资讯”
	public List<Article> getAllNews(){
		List<Article> newsList=new ArrayList();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select article_id,article_title,article_content,article_time,article_img,writer_id from article where article_type=1";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("ArticleDao:已经联合查询出高考新资讯");
				Article article=new Article();
				article.setArticleId(res.getInt("article_id"));
				article.setArticleTitle(res.getString("article_title"));
				article.setArticleContent(res.getString("article_content"));
				article.setArticleTime(res.getString("article_time"));
				article.setArticleImg(res.getString("article_img"));
				int writerId=res.getInt("article_id");
				WriterDao writerDao=new WriterDao();
				article.setWriter(writerDao.getWriterById(writerId));
				newsList.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ArticleDao:查询出的newslist长度为"+newsList.size());
		return newsList;
	}
	
	//获得所有“学长学姐有话说”
		public List<Article> getAllTalk(){
			List<Article> talkList=new ArrayList();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select article_id,article_title,article_content,article_img,article_time,writer_id from article where article_type=2";
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("ArticleDao:已经联合查询出talkList");
					Article article2=new Article();
					article2.setArticleId(res.getInt("article_id"));
					article2.setArticleTitle(res.getString("article_title"));
					article2.setArticleContent(res.getString("article_content"));
					article2.setArticleTime(res.getString("article_time"));
					article2.setArticleImg(res.getString("article_img"));
					int writerId=res.getInt("writer_id");
					WriterDao writerDao=new WriterDao();
					article2.setWriter(writerDao.getWriterById(writerId));
					talkList.add(article2);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("ArticleDao:查询出的talklist长度为"+talkList.size());
			return talkList;
		}

		//获得首页“高考新资讯”内容
		public List<Article> getIndexNews() {
			List<Article> newsList=new ArrayList();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select article_id,article_title,article_content,article_time,article_img,writer_id from article where article_type=1 limit 0,3";
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("ArticleDao:已经联合查询出高考新资讯");
					Article article=new Article();
					article.setArticleId(res.getInt("article_id"));
					article.setArticleTitle(res.getString("article_title"));
					article.setArticleContent(res.getString("article_content"));
					article.setArticleTime(res.getString("article_time"));
					article.setArticleImg(res.getString("article_img"));
					int writerId=res.getInt("article_id");
					WriterDao writerDao=new WriterDao();
					article.setWriter(writerDao.getWriterById(writerId));
					newsList.add(article);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("ArticleDao:查询出的indexNewslist长度为"+newsList.size());
			return newsList;
		}

		//获得首页“学长学姐有话说”内容
		public List<Article> getIndexTalk() {
			List<Article> talkList=new ArrayList();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select article_id,article_title,article_content,article_img,article_time,writer_id from article where article_type=2 limit 0,3";
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("ArticleDao:已经联合查询出indextalkList");
					Article article2=new Article();
					article2.setArticleId(res.getInt("article_id"));
					article2.setArticleTitle(res.getString("article_title"));
					article2.setArticleContent(res.getString("article_content"));
					article2.setArticleTime(res.getString("article_time"));
					article2.setArticleImg(res.getString("article_img"));
					int writerId=res.getInt("writer_id");
					WriterDao writerDao=new WriterDao();
					article2.setWriter(writerDao.getWriterById(writerId));
					talkList.add(article2);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("ArticleDao:查询出的indextalklist长度为"+talkList.size());
			return talkList;
		}
}
