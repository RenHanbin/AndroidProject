package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.CollectionMajorDao;
import dao.CollectionSchoolDao;

/**
 * Servlet implementation class CollectionSchoolServlet
 */
@WebServlet("/CollectionSchoolServlet")
public class CollectionSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionSchoolServlet() {
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
		System.out.println("收藏学校servlet:remark="+remark);
		int userId=Integer.parseInt(request.getParameter("userId"));
		int schoolId=Integer.parseInt(request.getParameter("schoolId"));
		System.out.print("userId="+userId);
		System.out.print("schoolId="+schoolId);
		if("addSchoolCollection".equals(remark)) {
			//添加收藏学校
			addSchoolCollection(userId,schoolId,request,response);
		}else if("deleteSchoolCollection".equals(remark)) {
			deleteSchoolCollection(userId,schoolId,request,response);
		}
	}

	//删除收藏学校
	private void deleteSchoolCollection(int userId, int schoolId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionSchoolDao collectionSchoolDao=new CollectionSchoolDao();
		boolean b=collectionSchoolDao.deleteCollectionSchool(userId,schoolId);
		JSONObject object=new JSONObject();
		if(b) {
			//注册成功
			System.out.println("取消收藏成功");
			object.put("success", "取消收藏成功");
		}else {
			//注册失败
			System.out.println("取消收藏失败");
			object.put("false", "取消收藏失败");
		}
		response.getWriter().append(object.toString());
	}

	//添加收藏学校
	private void addSchoolCollection(int userId, int schoolId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CollectionSchoolDao collectionSchoolDao=new CollectionSchoolDao();
		boolean b=collectionSchoolDao.addCollectionSchool(userId,schoolId);
		JSONObject object=new JSONObject();
		if(b) {
			//注册成功
			System.out.println("收藏成功");
			object.put("success", "收藏成功");
		}else {
			//注册失败
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
