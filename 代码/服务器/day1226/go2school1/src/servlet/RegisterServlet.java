package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userName=request.getParameter("userName");
		String userPwd=request.getParameter("userPwd");
		String userTel=request.getParameter("userTel");
		String userEmail=request.getParameter("userEmail");
		System.out.println(userName);
		System.out.println(userPwd);
		System.out.println(userTel);
		System.out.println(userEmail);
		UserDao userDao=new UserDao();
		boolean i;
		i=userDao.ifRegistered(userTel);
		JSONObject object=new JSONObject();
		if(i) {
			//该用户没有注册过
			boolean b=userDao.addUser(userName,userPwd,userTel,userEmail);
			if(b) {
				//注册成功
				object.put("success", "注册成功");
			}else {
				//注册失败
				object.put("false", "注册失败");
			}
		}else {
			//该用户注册过
			object.put("false2", "该手机号已注册");
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
