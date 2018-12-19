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

import bean.CommentBean;
import bean.FollowBean;
import dao.FollowDao;
import dao.MyCommentDao;

/**
 * Servlet implementation class MyFollowServlet
 */
@WebServlet("/MyFollowServlet")
public class MyFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFollowServlet() {
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
		System.out.println("MyFollowServlet:remark="+remark);
		if("getMyFollowList".equals(remark)) {
			FollowDao followDao = new FollowDao();
			List<FollowBean> followList = followDao.getFollow(userId);
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<followList.size();i++) {
				System.out.println("afndlknfldnla");
				JSONObject object=new JSONObject();
				object.put("followId",followList.get(i).getFollowUserId());
				object.put("folloewdUserId", followList.get(i).getFollowedUser().getUserId());
				object.put("followedUserHead",followList.get(i).getFollowedUser().getUserImage());
				object.put("followedUserName",followList.get(i).getFollowedUser().getUserName());
				jsonArray.put(object);
			}
			System.out.println("MyFollowServlet:arrayList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
		}else if("deleteMyFollowList".equals(remark)) {
			int followedUserId = Integer.parseInt(request.getParameter("followedUserId"));
			FollowDao followDao = new FollowDao();
			followDao.deleteMyFollow(userId,followedUserId);
			List<FollowBean> followList = followDao.getFollow(1);
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<followList.size();i++) {
				System.out.println("deleteMyFollowList");
				JSONObject object=new JSONObject();
				object.put("followId",followList.get(i).getFollowUserId());
				object.put("folloewdUserId", followList.get(i).getFollowedUser().getUserId());
				object.put("followedUserHead",followList.get(i).getFollowedUser().getUserImage());
				object.put("followedUserName",followList.get(i).getFollowedUser().getUserName());
				jsonArray.put(object);
			}
			System.out.println("MyFollowServlet:arrayList的长度为："+jsonArray.length());
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
