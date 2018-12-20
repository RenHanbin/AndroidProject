package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.FollowDao;

/**
 * Servlet implementation class FollowServlet
 */
@WebServlet("/FollowServlet")
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FollowServlet:remark=");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String remark=request.getParameter("remark");
		int userId=Integer.parseInt(request.getParameter("userId"));
		System.out.println("FollowServlet:remark="+remark);
		if("FollowInsert".equals(remark)) {
			//添加关注
			int followedUserId=Integer.parseInt(request.getParameter("followedUserId"));
			followInsert(userId,followedUserId,request,response);	
		}else if("FollowDelete".equals(remark)) {
			//取消关注
			int followedUserId=Integer.parseInt(request.getParameter("followedUserId"));
			followDelete(userId,followedUserId,request,response);
		}else if("ifExistHuanZhu".equals(remark)) {
			//是否存在关注
			int followedUserId=Integer.parseInt(request.getParameter("followedUserId"));
			ifExistGuanZhua(userId,followedUserId,request,response);
		}
		
	}
	//添加关注
	private void followInsert(int userId, int followedUserId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		FollowDao followDao=new FollowDao();
		Boolean addreturn= followDao.insertFollow(userId, followedUserId);
		JSONObject object=new JSONObject();
		if(addreturn) {
			object.put("success", "添加关注成功");
		}else {
			object.put("fail", "添加关注失败");
		}
		response.getWriter().append(object.toString());
	}
	//删除关注
	private void followDelete(int userId, int followedUserId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		FollowDao followDao=new FollowDao();
		Boolean deletereturn=followDao.deteleFollew(userId, followedUserId);
		JSONObject object=new JSONObject();
		if(deletereturn) {
			object.put("success", "删除关注成功");
		}else {
			object.put("fail", "删除关注失败");
		}
		response.getWriter().append(object.toString());
	}
			
	//判断用户是否关注过
	private void ifExistGuanZhua(int userId, int followedUserId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		FollowDao followDao=new FollowDao();
		boolean b=followDao.ifExistHuanZhu(userId, followedUserId);
		JSONObject object=new JSONObject();
		if(b) {
			//已经收藏过
			System.out.println("关注过");
			object.put("success", "已关注");
		}else {
			//未收藏过
			System.out.println("没有关注过");
			object.put("false", "未关注");
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
