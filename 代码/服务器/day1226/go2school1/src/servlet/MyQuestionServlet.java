package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.CommentBean;
import bean.FollowBean;
import bean.MajorBean;
import bean.QuestionBean;
import dao.FollowDao;
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
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String remark=request.getParameter("remark");
		int userId=Integer.parseInt(request.getParameter("userId"));
//		int userId=1;
		System.out.println("MyQuestionServlet:remark="+remark);
		if("getMyQuestionList".equals(remark)) {
			MyQuestDao myQuestDao=new MyQuestDao();
			List<QuestionBean> questionList=myQuestDao.getQuestList(userId); //userId=1
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
			System.out.println("myquestion,userId=1:"+jsonArray.toString());
			response.getWriter().append(jsonArray.toString());
		}else if("getAllQuestionList".equals(remark)) {  //社区问题
			MyQuestDao myQuestDao=new MyQuestDao();
			List<QuestionBean> questionList=myQuestDao.getAllQuestList(); //userId=1
			System.out.println(questionList.toString());
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<questionList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("questionId",questionList.get(i).getQuestionId());
				object.put("questionTitle",questionList.get(i).getQuestionTitle());
				object.put("questionImg", questionList.get(i).getQuestionImg());
				int attenNum = new MyQuestDao().getAttenNum(questionList.get(i).getQuestionId()); 
				object.put("attenNum",attenNum);
				jsonArray.put(object);
			}
			response.getWriter().append(jsonArray.toString());
		}else if("getAllAttenList".equals(remark)) {  //社区关注
			List<FollowBean> followList = new FollowDao().getFollow(userId);
			List<QuestionBean> questionAllList=new ArrayList<QuestionBean>();
			for(int i=0;i<followList.size();i++) {
				//找到一个followedUserId
				int followedUserId = followList.get(i).getFollowedUser().getUserId();
				//通过followedUserId找到他发表的问题List
				MyQuestDao myQuestDao = new MyQuestDao();
				List<QuestionBean> questionList =myQuestDao.getQuestionByFollowedUserId(followedUserId);
				for(int j=0;j<questionList.size();j++) {
					questionAllList.add(questionList.get(j));
				}
			}
			System.out.println("zxcvbnm,.sdfghjkl;"+questionAllList.size());
			JSONArray jsonArray=new JSONArray();
			for(int m=0;m<questionAllList.size();m++) {
				JSONObject object=new JSONObject();
				object.put("questionId",questionAllList.get(m).getQuestionId());
				object.put("questionTitle",questionAllList.get(m).getQuestionTitle());
				object.put("questionDescribe", questionAllList.get(m).getQuestionDiscribe());
				object.put("questionTime", questionAllList.get(m).getQuestionTime());
				object.put("userImg", questionAllList.get(m).getQuestionUser().getUserImg());
				object.put("userName", questionAllList.get(m).getQuestionUser().getUserName());
				jsonArray.put(object);
			}
			System.out.println("list:"+jsonArray.toString());
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
