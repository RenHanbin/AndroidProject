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
import dao.MagorHot;
import dao.MajorDao;

/**
 * Servlet implementation class MajorServlet
 */
@WebServlet("/MajorServlet")
public class MajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajorServlet() {
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
		String majorName = request.getParameter("majorName");
		System.out.println(majorName);
		System.out.println("majorServlet:remark="+remark);
		if("getMajorList".equals(remark)) {
			MajorDao majorDao=new MajorDao();
			List<MajorBean> majorList=majorDao.getMajorList();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<majorList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("majorId",majorList.get(i).getMajorId());
				object.put("majorName",majorList.get(i).getMajorName());
				object.put("majorTypeName",majorList.get(i).getMajorTypeName());
				object.put("majorWorkPercent",majorList.get(i).getMajorWorkPercent());
				object.put("majorSalary",majorList.get(i).getMajorSalary());
				jsonArray.put(object);
			}
			System.out.println("MajorServlet:arrayList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
		}else if("getMajorHotList".equals(remark)) {  //获取所有专业
			MagorHot majorHot=new MagorHot();
			List<MajorBean> majorList=majorHot.getMajorList();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<majorList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("majorId",majorList.get(i).getMajorId());
				object.put("majorName",majorList.get(i).getMajorName());
				object.put("majorTypeName",majorList.get(i).getMajorTypeName());
				object.put("majorWant",majorList.get(i).getMajorWant());
				object.put("majorNeed",majorList.get(i).getMajorNeed());
				object.put("majorInf", majorList.get(i).getMajorInf());
				object.put("majorSubject", majorList.get(i).getMajorSubject());
				object.put("majorWork", majorList.get(i).getMajorWork());
				System.out.println(object.toString());
				jsonArray.put(object);
			}
			System.out.println("MajorServlet:arrayList的长度为："+jsonArray.length());
			response.getWriter().append(jsonArray.toString());
		}else if("getMajorHotSubList".equals(remark)) {    //搜索专业
			MagorHot majorHot=new MagorHot();
			List<MajorBean> majorList=majorHot.getMajorSubList(majorName);
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<majorList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("majorId",majorList.get(i).getMajorId());
				object.put("majorName",majorList.get(i).getMajorName());
				object.put("majorTypeName",majorList.get(i).getMajorTypeName());
				object.put("majorWant",majorList.get(i).getMajorWant());
				object.put("majorNeed",majorList.get(i).getMajorNeed());
				object.put("majorInf", majorList.get(i).getMajorInf());
				object.put("majorSubject", majorList.get(i).getMajorSubject());
				object.put("majorWork", majorList.get(i).getMajorWork());
				jsonArray.put(object);
				System.out.println(object);
			}
			System.out.println(majorList);
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
