package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Article;
import dao.ArticleDao;

/**
 * Servlet implementation class ZiXunServlet
 */
@WebServlet("/ZiXunServlet")
public class ZiXunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZiXunServlet() {
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
		System.out.println("ZixunServlet:remark="+remark);
		if("getNewsList".equals(remark)) {
			ArticleDao articleDao=new ArticleDao();
			List<Article> newsList=articleDao.getAllNews();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<newsList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("articleId",newsList.get(i).getArticleId());
				object.put("articleTitle",newsList.get(i).getArticleTitle());
				object.put("articleContent",newsList.get(i).getArticleContent());
				object.put("articleImg",newsList.get(i).getArticleImg());
				object.put("articleTime",newsList.get(i).getArticleTime());
				object.put("articleWriter",newsList.get(i).getWriter());
				jsonArray.put(object);
			}
			System.out.println("ArticleServlet:newsList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
			
		}else if("getTalkList".equals(remark)) {
			ArticleDao articleDao=new ArticleDao();
			List<Article> talkList=articleDao.getAllTalk();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<talkList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("articleId",talkList.get(i).getArticleId());
				object.put("articleTitle",talkList.get(i).getArticleTitle());
				object.put("articleContent",talkList.get(i).getArticleContent());
				object.put("articleImg",talkList.get(i).getArticleImg());
				object.put("articleTime",talkList.get(i).getArticleTime());
				object.put("articleWriter",talkList.get(i).getWriter());
				jsonArray.put(object);
			}
			System.out.println("ArticleServlet:talkList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
		}else if("getIndexNewsList".equals(remark)) {
			JSONArray jsonArray=getIndexNewsList();
			System.out.println("ArticleServlet:indexNewsList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
		}else if("getIndexTalkList".equals(remark)) {
			JSONArray jsonArray=getIndexTalkList();
			System.out.println("ArticleServlet:indextalkList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
		}
		
	}

	//获得“学长学姐有话说”
	private JSONArray getIndexTalkList() {
		// TODO Auto-generated method stub
		ArticleDao articleDao=new ArticleDao();
		List<Article> talkList=articleDao.getIndexTalk();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<talkList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("articleId",talkList.get(i).getArticleId());
			object.put("articleTitle",talkList.get(i).getArticleTitle());
			object.put("articleContent",talkList.get(i).getArticleContent());
			object.put("articleImg",talkList.get(i).getArticleImg());
			object.put("articleTime",talkList.get(i).getArticleTime());
			object.put("articleWriter",talkList.get(i).getWriter());
			jsonArray.put(object);
		}
		return jsonArray;
	}

	//获得首页“高考新资讯内容”
	private JSONArray getIndexNewsList() {
		ArticleDao articleDao=new ArticleDao();
		List<Article> newsList=articleDao.getIndexNews();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<newsList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("articleId",newsList.get(i).getArticleId());
			object.put("articleTitle",newsList.get(i).getArticleTitle());
			object.put("articleContent",newsList.get(i).getArticleContent());
			object.put("articleImg",newsList.get(i).getArticleImg());
			object.put("articleTime",newsList.get(i).getArticleTime());
			object.put("articleWriter",newsList.get(i).getWriter());
			jsonArray.put(object);
		}
		return jsonArray;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
