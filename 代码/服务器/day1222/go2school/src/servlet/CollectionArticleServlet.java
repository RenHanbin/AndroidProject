package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Article;
import bean.CollectionArticleBean;
import dao.CollectionArticleDao;

/**
 * Servlet implementation class CollectionArticleServlet
 */
@WebServlet("/CollectionArticleServlet")
public class CollectionArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String remark=request.getParameter("remark");
		int userId=Integer.parseInt(request.getParameter("userId"));
		System.out.println("CollectionArticleServlet:remark="+remark);
		if("collectionArticleList".equals(remark)) {
			//获得收藏文章列表
			collectionArticleList(request,response,userId);
		}else if("collectionArticleListThree".equals(remark)) {
			//显示收藏文章的具体内容的三级页面
			int articleId=Integer.parseInt(request.getParameter("articleId"));
			collectionArticleListListThree(userId,articleId,request,response);	
		}else if("ifExistCollectionArticle".equals(remark)) {
			//判断是否收藏过
			int articleId=Integer.parseInt(request.getParameter("articleId"));
			ifExistCollectionArticle(userId,articleId,request,response);
		}else if("addArticleCollection".equals(remark)) {
			//添加收藏
			int articleId=Integer.parseInt(request.getParameter("articleId"));
			addArticleCollection(userId,articleId,request,response);
		}else if("deleteArticleCollection".equals(remark)) {
			//取消收藏
			int articleId=Integer.parseInt(request.getParameter("articleId"));
			deleteArticelCollection(userId,articleId,request,response);
		}
	}

	//取消收藏
	private void deleteArticelCollection(int userId, int articleId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionArticleDao collectionArticleDao=new CollectionArticleDao();
		boolean b=collectionArticleDao.deleteCollectionMajor(userId,articleId);
		JSONObject object=new JSONObject();
		if(b) {
			//注册成功
			System.out.println("取消收藏成功");
			object.put("success", "取消收藏成功");
		}else {
			//注册失败
			System.out.println("取消收藏失败");
			object.put("false", "取消收藏失败");
		}
		response.getWriter().append(object.toString());
		
	}

	//添加收藏
	private void addArticleCollection(int userId, int articleId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionArticleDao collectionArticleDao=new CollectionArticleDao();
		boolean b=collectionArticleDao.addCollectionMajor(userId, articleId);
		JSONObject object=new JSONObject();
		if(b) {
			//注册成功
			System.out.println("收藏成功");
			object.put("success", "收藏成功");
		}else {
			//注册失败
			System.out.println("收藏失败");
			object.put("false", "收藏失败");
		}
		response.getWriter().append(object.toString());
		
	}

	//判断是否收藏过文章
	private void ifExistCollectionArticle(int userId, int articleId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionArticleDao collectionArticleDao=new CollectionArticleDao();
		boolean b=collectionArticleDao.ifExist(userId,articleId);
		JSONObject object=new JSONObject();
		if(b) {
			//已经收藏过
			System.out.println("收藏过");
			object.put("success", "已收藏");
		}else {
			//未收藏过
			System.out.println("没有搜藏过");
			object.put("false", "未收藏");
		}
		response.getWriter().append(object.toString());
	}

	//显示收藏文章的具体内容的三级页面
	private void collectionArticleListListThree(int userId, int articleId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionArticleDao collectionArticleDao=new CollectionArticleDao();
		Article article=collectionArticleDao.getArticleByArticleId(articleId);
		JSONObject object=new JSONObject();
		object.put("writerName", article.getWriter().getWriterName());
		object.put("writerImg", article.getWriter().getWriterImg());
		object.put("articleTitle", article.getArticleTitle());
		object.put("articleContent", article.getArticleContent());
		object.put("articleTime", article.getArticleTime());
		response.getWriter().append(object.toString());
		
	}

	//获得收藏文章列表
	private void collectionArticleList(HttpServletRequest request, HttpServletResponse response, int userId) throws IOException {
		// TODO Auto-generated method stub
		CollectionArticleDao collectionArticleDao=new CollectionArticleDao();
		List<Article> articleList=new ArrayList<Article>();
		List<CollectionArticleBean> collectionArticleIdList=collectionArticleDao.getArticleIdListByUserId(userId);
		for(int i=0;i<collectionArticleIdList.size();i++) {
			System.out.println("articleId:"+collectionArticleIdList.get(i).getArticleId());
			int articleId=collectionArticleIdList.get(i).getArticleId();
			Article article=collectionArticleDao.getArticleByArticleId(articleId);
			int commentCount=collectionArticleDao.getCommentCountArticleId(articleId);
			System.out.println("commentCount:"+commentCount);
			article.setCommentCount(commentCount);
			articleList.add(article);
		}
		System.out.println("articleList.size():"+articleList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<articleList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("articleId", articleList.get(i).getArticleId());
			object.put("articleTitle", articleList.get(i).getArticleTitle());
			object.put("articleImg", articleList.get(i).getArticleImg());
			object.put("articleTime", articleList.get(i).getArticleTime());
			object.put("commentCount", articleList.get(i).getCommentCount());
			object.put("articleContent", articleList.get(i).getArticleContent());
			object.put("articleWriter", articleList.get(i).getWriter());
			jsonArray.put(object);
		}
		System.out.println("CollectionArticleServlet：jsonArray的长度为："+jsonArray.length());
		response.getWriter().append(jsonArray.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
