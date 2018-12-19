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

import bean.AnswerBean;
import bean.CollectionQuestionBean;
import bean.QuestionBean;
import dao.CollectionQuestionDao;
import dao.CollectionSchoolDao;

/**
 * Servlet implementation class CollectionQuestionServlet
 */
@WebServlet("/CollectionQuestionServlet")
public class CollectionQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionQuestionServlet() {
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
		System.out.println("CollectionQuestionServlet:remark="+remark);
		if("collectionQuestionList".equals(remark)) {
			//获得收藏的问题列表
			collectionQuestionList(userId,request,response);
			
		}else if("collectionQuestionListThree".equals(remark)) {
			//获得收藏问题的三级界面列表
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			collectionQuestionListThree(userId,questionId,request,response);	
		}else if("collectionQuestionListThreeAnswer".equals(remark)) {
			//获得收藏问题的回答列表
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			collectionQuestionListThreeAnswer(userId,questionId,request,response);
		}else if("collectionQuestionListDelete".equals(remark)) {
			//取消收藏
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			collectionQuestionListDelete(questionId,userId,request,response);
		}else if("collectionQuestionListInsert".equals(remark)) {
			//添加收藏
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			collectionQuestionListInsert(questionId,userId,request,response);	
		}else if("ifExistCollectionQuestion".equals(remark)) {
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			ifExistCollectionQuestion(userId,questionId,request,response);
		}
	}


	//判断此问题是否收藏过
	private void ifExistCollectionQuestion(int userId, int questionId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionQuestionDao collectionQuestionDao=new CollectionQuestionDao();
		boolean b=collectionQuestionDao.ifExist(userId,questionId);
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

	//添加收藏
	private void collectionQuestionListInsert(int questionId, int userId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionQuestionDao collectionQuestionDao=new CollectionQuestionDao();
		Boolean returnn= collectionQuestionDao.insertQuestionIdListByUserIdQuestionId(userId, questionId);
		JSONObject object=new JSONObject();
		object.put("returnn", returnn);
		response.getWriter().append(object.toString());
	}

	//删除收藏的问题
	private void collectionQuestionListDelete(int questionId, int userId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionQuestionDao collectionQuestionDao=new CollectionQuestionDao();
		Boolean returne=collectionQuestionDao.deteleQuestionIdListByUserIdQuestionId(userId, questionId);
		JSONObject object=new JSONObject();
		object.put("returne", returne);
		response.getWriter().append(object.toString());
	}

	//获得收藏问题的回答列表
	private void collectionQuestionListThreeAnswer(int userId, int questionId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionQuestionDao collectionQuestionDao=new CollectionQuestionDao();	 
		List<AnswerBean> answerList=collectionQuestionDao.getAnswerListByquestionId(questionId);
		System.out.println("answerList.size():"+answerList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<answerList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("answerTime", answerList.get(i).getAnswerTime());
			object.put("answerContent", answerList.get(i).getAnswerContent());
			object.put("answerUserName", answerList.get(i).getUser().getUserName());
			object.put("answerUserImg", answerList.get(i).getUser().getUserImg());
			jsonArray.put(object);
		}
		System.out.println("CollectionQuestionServlet：jsonArray的长度为："+jsonArray.length());
		response.getWriter().append(jsonArray.toString());
	}

	//获得收藏问题三级界面列表
	private void collectionQuestionListThree(int userId, int questionId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionQuestionDao collectionQuestionDao=new CollectionQuestionDao();
		QuestionBean question=collectionQuestionDao.getQuestionByQuestionId(questionId);
		System.out.println("questionTitle"+question.getQuestionTitle());
		JSONObject object=new JSONObject();
		object.put("questionTitle", question.getQuestionTitle());
		response.getWriter().append(object.toString());
	}

	//获得收藏问题的列表
	private void collectionQuestionList(int userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionQuestionDao collectionQuestionDao=new CollectionQuestionDao();
		List<QuestionBean> questionList=new ArrayList<QuestionBean>();
		List<CollectionQuestionBean> collectionQuestionIdList=collectionQuestionDao.getQuestionIdListByUserId(userId);
		for(int i=0;i<collectionQuestionIdList.size();i++) {
			System.out.println("questionId:"+collectionQuestionIdList.get(i).getQusetionId());
			int questionId=collectionQuestionIdList.get(i).getQusetionId();
			QuestionBean question=collectionQuestionDao.getQuestionByQuestionId(questionId);
			int userCount=collectionQuestionDao.getUserCountByQuestionId(questionId);
			System.out.println("commentCount:"+userCount);
			question.setUserCount(userCount);
			questionList.add(question);
		}
		System.out.println("questionList.size():"+questionList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<questionList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("questionId", questionList.get(i).getQuestionId());
			object.put("questionTitle", questionList.get(i).getQuestionTitle());
			object.put("userImg", questionList.get(i).getQuestionUser().getUserImg());
			object.put("userName", questionList.get(i).getQuestionUser().getUserName());
			object.put("userCount", questionList.get(i).getUserCount());
			jsonArray.put(object);
		}
		System.out.println("CollectionQuestionServlet：jsonArray的长度为："+jsonArray.length());
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
