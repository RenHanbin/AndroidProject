package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.UserBean;
import dao.UserDao;

/**
 * Servlet implementation class SetUpUserServlet
 */
@WebServlet("/SetUpUserServlet")
public class SetUpUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetUpUserServlet() {
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
		System.out.println("SetUpUserServlet:remark="+remark);
		if("setUpUser".equals(remark)) {
			UserDao userDao=new UserDao();
			UserBean userbean=userDao.getUserByUserId(userId);
			JSONObject object=new JSONObject();
			String str=userbean.getUserTel();
			String strFirst=str.substring(0, 3);
			String strLast=str.substring(str.length()-4, str.length());
			String userTel=strFirst+"****"+strLast;
			object.put("userTel", userTel);
			String str1=userbean.getUserEmail();
			String strFirst1=str1.substring(0, 3);
			String strLast1=str1.substring(7, str1.length());
			String userEmail=strFirst1+"****"+strLast1;
			object.put("userEmail",userEmail);
			object.put("userPassword", userbean.getUserPassword());
			response.getWriter().append(object.toString());
		}else if("setUpUserPassword".equals(remark)) {
			String password=request.getParameter("userPassword");
			UserDao userDao=new UserDao();
			userDao.updateUserPasswordByUserId(userId, password);
			UserBean userbean=userDao.getUserByUserId(userId);
			JSONObject object=new JSONObject();
			object.put("userPassword", userbean.getUserPassword());
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
