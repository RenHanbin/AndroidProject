package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.AnswerBean;
import bean.CommentBean;
import bean.MajorBean;
import dao.MajorDao;
import dao.MyCommentDao;
import dao.MyQuestDao;

/**
 * Servlet implementation class MyCommentServlet
 */
@WebServlet("/MyCommentServlet")
public class MyCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCommentServlet() {
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
		System.out.println("MyCommentServlet:remark="+remark);
		if("getMyCommentList".equals(remark)) {
			MyCommentDao commentDao = new MyCommentDao();
			List<CommentBean> commentList = commentDao.getComm(1);
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<commentList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("commentId",commentList.get(i).getCommentId());
				object.put("commentContent",commentList.get(i).getCommentContent());
				object.put("commentTime",commentList.get(i).getCommentTime());
//				object.put("answerContent",commentList.get(i).getAnswer().getAnswerContent());
				object.put("articleTitle",commentList.get(i).getArticle().getArticleTitle());
				jsonArray.put(object);
			}
			System.out.println("MyCommentServlet:arrayList的长度为："+jsonArray.length());
//			System.out.println(commentList);
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
