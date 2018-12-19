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

import bean.MajorBean;
import bean.UserBean;
import dao.MajorDao;
import dao.MyUserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		System.out.println("userServlet:remark="+remark);
		if("getUser".equals(remark)) {
//			int userId = Integer.parseInt(request.getParameter("userId"));
			int userId=1;
			UserBean user = new MyUserDao().getUser(userId);
			System.out.println(user+"");
			@SuppressWarnings("unused")
			MyUserDao myUserDao=new MyUserDao();
			JSONObject object=new JSONObject();
			object.put("userId",user.getUserId());
			object.put("userImage",user.getUserImg());
			object.put("userName",user.getUserName());
			object.put("userSex",user.getUserSex());
			object.put("userTel",user.getUserTel());
			object.put("userEmail",user.getUserEmail());
			int fansNum = new MyUserDao().getFansNum(userId); 
			object.put("fansNum",fansNum);
			int attenNum = new MyUserDao().getAttenNum(userId);
			object.put("attenNum",attenNum);
			System.out.println("关注人数"+attenNum);
			System.out.println("MyUserObject添加成功");
			response.getWriter().append(object.toString());
		}else if("getUserBalance".equals(remark)) {
//			int userId = Integer.parseInt(request.getParameter("userId"));
			int userId=1;
			UserBean user=new MyUserDao().getUserBalance(userId);
			JSONObject object=new JSONObject();
			object.put("userId",user.getUserId());
			object.put("userBalance",user.getUserBalance());
			System.out.println(object.toString());
			response.getWriter().append(object.toString());
		}else if("changeUser".equals(remark)) {
//			int userId = Integer.parseInt(request.getParameter("userId"));
			int userId=1;
			String userName =request.getParameter("name");
			String userSex =request.getParameter("sex");
			String userPhone =request.getParameter("phone");
			String userEmail =request.getParameter("email");
			System.out.println("name:"+userName);
			new MyUserDao().changeUser(userId,userName,userSex,userPhone,userEmail);
			UserBean user = new MyUserDao().getUser(userId);
			System.out.println(user.toString());
			JSONObject object=new JSONObject();
			object.put("userId",user.getUserId());
//        object.put("userImage",user.getUserImage());
			object.put("userName",user.getUserName());
			object.put("userSex",user.getUserSex());
			object.put("userTel",user.getUserTel());
			object.put("userEmail",user.getUserEmail());
			System.out.println(object.toString());
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
