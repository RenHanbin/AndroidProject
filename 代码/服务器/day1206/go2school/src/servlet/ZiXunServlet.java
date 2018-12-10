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
import bean.Article2;
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
			List<Article2> talkList=articleDao.getAllTalk();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<talkList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("article2Id",talkList.get(i).getArticle2Id());
				object.put("article2Title",talkList.get(i).getArticle2Title());
				object.put("article2Content",talkList.get(i).getArticle2Content());
				object.put("article2Img",talkList.get(i).getArticle2Img());
				object.put("article2Time",talkList.get(i).getArticle2Time());
				object.put("article2Writer",talkList.get(i).getWriter());
				jsonArray.put(object);
			}
			System.out.println("ArticleServlet:talkList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
