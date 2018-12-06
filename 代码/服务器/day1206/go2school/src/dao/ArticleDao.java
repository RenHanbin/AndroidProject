package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Article;
import bean.Article2;
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
			String sql="select article_id,article_title,article_content,article_time,writer_id from article";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("ArticleDao:已经联合查询出高考新资讯");
				Article article=new Article();
				article.setArticleId(res.getInt("article_id"));
				article.setArticleTitle(res.getString("article_title"));
				article.setArticleContent(res.getString("article_content"));
				article.setArticleTime(res.getString("article_time"));
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
		public List<Article2> getAllTalk(){
			List<Article2> talkList=new ArrayList();
			Connection conn=null;
			try {
				conn = DataBase.getConnection();
				PreparedStatement pre=null;
				ResultSet res=null;
				String sql="select article2_id,article2_title,article2_content,article2_img,article2_time,writer_id from article2";
				pre=conn.prepareStatement(sql);
				res=pre.executeQuery();
				while(res.next()) {
					System.out.print("ArticleDao:已经联合查询出talkList");
					Article2 article2=new Article2();
					article2.setArticle2Id(res.getInt("article2_id"));
					article2.setArticle2Title(res.getString("article2_title"));
					article2.setArticle2Content(res.getString("article2_content"));
					article2.setArticle2Time(res.getString("article2_time"));
					article2.setArticle2Img(res.getString("article2_img"));
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
}
