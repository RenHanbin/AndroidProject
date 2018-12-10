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
		System.out.println("majorServlet:remark="+remark);
		if("getMajorList".equals(remark)) {
			getMajorList(request,response);
		}else if("getMajorLearnList".equals(remark)) {
			getMajorLearn(request,response);
		}else if("getMajorOutList".equals(remark)) {
			getMajorOut(request,response);
		}else if("getMajorWorkList".equals(remark)) {
			getMajorWork(request,response);
		}
	}

	private void getMajorWork(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MajorDao majorDao=new MajorDao();
		List<MajorBean> majorWorkList=majorDao.getMajorWorkList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<majorWorkList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorId",majorWorkList.get(i).getMajorId());
			object.put("majorName",majorWorkList.get(i).getMajorName());
			object.put("majorWorkPercent",majorWorkList.get(i).getMajorWorkPercent());
			object.put("majorStudy",majorWorkList.get(i).getMajorStudy());
			object.put("majorGo",majorWorkList.get(i).getMajorGo());
			jsonArray.put(object);
		}
		System.out.println("MajorServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getMajorOut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MajorDao majorDao=new MajorDao();
		List<MajorBean> majorOutList=majorDao.getMajorOutList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<majorOutList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorId",majorOutList.get(i).getMajorId());
			object.put("majorName",majorOutList.get(i).getMajorName());
			object.put("majorWorkPercent",majorOutList.get(i).getMajorWorkPercent());
			object.put("majorStudy",majorOutList.get(i).getMajorStudy());
			object.put("majorGo",majorOutList.get(i).getMajorGo());
			jsonArray.put(object);
		}
		System.out.println("MajorServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getMajorLearn(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MajorDao majorDao=new MajorDao();
		List<MajorBean> majorLearnList=majorDao.getMajorLearnList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<majorLearnList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorId",majorLearnList.get(i).getMajorId());
			object.put("majorName",majorLearnList.get(i).getMajorName());
			object.put("majorWorkPercent",majorLearnList.get(i).getMajorWorkPercent());
			object.put("majorStudy",majorLearnList.get(i).getMajorStudy());
			object.put("majorGo",majorLearnList.get(i).getMajorGo());
			jsonArray.put(object);
		}
		System.out.println("MajorServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getMajorList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
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
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
