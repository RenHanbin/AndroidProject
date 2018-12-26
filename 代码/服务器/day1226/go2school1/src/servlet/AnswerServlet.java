package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.AnswerBean;
import dao.AnswerDao;

/**
 * Servlet implementation class AnsweServlet
 */
@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerServlet() {
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
		int questionId=Integer.parseInt(request.getParameter("questionId"));
		String answerContent=request.getParameter("answerContent");
		System.out.println("AnswerServlet:remark="+remark);
		if("addAnswer".equals(remark)) {
			addAnswer(userId,questionId,answerContent,request,response);
		}else if("Answer".equals(remark)) {
			Answer(userId,questionId,request,response);
		}		
		
	}

	private void Answer(int userId, int questionId, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		AnswerDao answerDao=new AnswerDao();
		try {
			int answerId = answerDao.getAnswerId(userId, questionId);
			AnswerBean answer = new AnswerBean();
			answer = answerDao.getAnswerById(answerId);
			JSONObject object=new JSONObject();
			object.put("answerId", answer.getAnswerId());
			object.put("questionId", answer.getQuestionId());
			object.put("userId", answer.getUserId());
			object.put("answerContent", answer.getAnswerContent());
			object.put("answerTime", answer.getAnswerTime());
			object.put("answerUserName", answer.getAnswerUserName());
			object.put("answerUserImg", answer.getAnswerUserImg());
			System.out.println(object.toString());
			response.getWriter().append(object.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//添加回答
	private void addAnswer(int userId, int questionId, String answerContent, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		AnswerDao answerDao=new AnswerDao();
		boolean b=answerDao.addAnswer(userId,questionId,answerContent);
		JSONObject object=new JSONObject();
		if(b) {
			//发布成功
			System.out.println("收藏成功");
			object.put("success", "收藏成功");
		}else {
			//发布失败
			System.out.println("收藏失败");
			object.put("false", "收藏失败");
		}
		response.getWriter().append(object.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
