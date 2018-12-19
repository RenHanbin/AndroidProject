package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.QuestionBean;
import dao.QuestionDao;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
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
		System.out.println("QuestionServlet:remark="+remark);
		if("addQuestion".equals(remark)) {
			String questionTitle=request.getParameter("questionTitle");
			String questionDiscribe=request.getParameter("questionDiscribe");
			QuestionDao questionDao=new QuestionDao();
			List<QuestionBean> questionList=questionDao.getAllQuestion();
			int questionId=questionList.size()+1;
			Boolean addreturn=questionDao.addQuestion(questionId, questionTitle, questionDiscribe, userId);
			JSONObject object=new JSONObject();
			if(addreturn) {
				object.put("success", "添加成功");
			}else {
				object.put("fail", "添加失败");
			}
			response.getWriter().append(object.toString());
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
