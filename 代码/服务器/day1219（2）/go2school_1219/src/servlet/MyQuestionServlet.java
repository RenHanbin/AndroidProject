package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.CommentBean;
import bean.MajorBean;
import bean.QuestionBean;
import dao.MyCommentDao;
import dao.MyQuestDao;
import dao.MyUserDao;

/**
 * Servlet implementation class MyQuestionServlet
 */
@WebServlet("/MyQuestionServlet")
public class MyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQuestionServlet() {
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
//		int userId=Integer.parseInt(request.getParameter("userId"));
		int userId=1;
		System.out.println("MyQuestionServlet:remark="+remark);
		if("getMyQuestionList".equals(remark)) {
			MyQuestDao myQuestDao=new MyQuestDao();
			List<QuestionBean> questionList=myQuestDao.getQuestList(userId); //userId=1
			System.out.println(questionList.toString());
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<questionList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("questionId",questionList.get(i).getQuestionId());
				object.put("questionTitle",questionList.get(i).getQuestionTitle());
				object.put("questionDiscribe",questionList.get(i).getQuestionDiscribe());
				object.put("questionTime",questionList.get(i).getQuestionTime());
				int attenNum = new MyQuestDao().getAttenNum(questionList.get(i).getQuestionId()); 
				object.put("attenNum",attenNum);
				int commNum = new MyQuestDao().getCommNum(userId);
				object.put("commNum",commNum);
				jsonArray.put(object);
			}
			System.out.println("MyQuestServlet:arrayList长度为"+jsonArray.length());
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
