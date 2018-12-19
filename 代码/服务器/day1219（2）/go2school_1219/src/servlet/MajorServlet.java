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
import bean.MajorTypeBean;
import dao.MagorHot;
import dao.MajorDao;
import dao.SubjectDao;

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
			//专业排名——按照考研率
			getMajorLearn(request,response);
		}else if("getMajorOutList".equals(remark)) {
			//专业排名——按照出国率
			getMajorOut(request,response);
		}else if("getMajorWorkList".equals(remark)) {
			//专业排名——按照就业率
			getMajorWork(request,response);
		}else if("getMajorListByName".equals(remark)) {
			String majorName = request.getParameter("majorName");
			getMajorListByName(majorName,request,response);
		}else if("getMajorTypeList".equals(remark)) {
			getMajorTypeList(request,response);
		}else if("getMajorTypeListByName".equals(remark)){
			String content = request.getParameter("content");
			getMajorTypeListByName(content,request,response);
		}else if("getMajorHotList".equals(remark)) {  
			getMajorList(request,response);
		}else if("getMajorHotSubList".equals(remark)) {    
			//搜索专业
			String majorName = request.getParameter("majorName");
			System.out.println(majorName);
			getMajorHotSubList(majorName,request,response);
			
		}
	}
	
	//获得搜索的专业
	private void getMajorHotSubList(String majorName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
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
			object.put("majorInf", majorList.get(i).getMajorIntroduce());
			object.put("majorSubject", majorList.get(i).getMajorSubject());
			object.put("majorWork", majorList.get(i).getMajorWork());
			jsonArray.put(object);
			System.out.println(object);
		}
		System.out.println(majorList);
		response.getWriter().append(jsonArray.toString());
	}

	//根据课程名找到专业类别
	private void getMajorTypeListByName(String content, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SubjectDao subjectDao = new SubjectDao();
		int subId = subjectDao.getSubId(content);
		int typeId = subjectDao.getTypeId(subId);
		MajorDao majorDao=new MajorDao();
		List<MajorTypeBean> typeList=majorDao.getMajorTypeListById(typeId);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<typeList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorTypeId",typeList.get(i).getMajorTypeId());
			object.put("majorTypeName",typeList.get(i).getMajorTypeName());
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

	//获取专业类别列表
	private void getMajorTypeList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MajorDao majorDao=new MajorDao();
		List<MajorTypeBean> typeList=majorDao.getMajorTypeList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<typeList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorTypeId",typeList.get(i).getMajorTypeId());
			object.put("majorTypeName",typeList.get(i).getMajorTypeName());
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

	private void getMajorListByName(String majorName, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MajorDao majorDao=new MajorDao();
		List<MajorBean> majorList=majorDao.getMajorListByTypeName(majorName);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<majorList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorId",majorList.get(i).getMajorId());
			object.put("majorName",majorList.get(i).getMajorName());
			object.put("majorTypeName",majorList.get(i).getMajorTypeName());
			object.put("majorWorkPercent",majorList.get(i).getMajorWorkPercent());
			object.put("majorSalary",majorList.get(i).getMajorSalary());
			object.put("majorIntroduce", majorList.get(i).getMajorIntroduce());
			object.put("majorSubject", majorList.get(i).getMajorSubject());
			object.put("majorWork", majorList.get(i).getMajorWork());
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
	 * 专业排名
	 * @param request
	 * @param response
	 */
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
			object.put("majorTypeName",majorWorkList.get(i).getMajorTypeName());
			object.put("majorIntroduce", majorWorkList.get(i).getMajorIntroduce());
			object.put("majorSubject", majorWorkList.get(i).getMajorSubject());
			object.put("majorWork", majorWorkList.get(i).getMajorWork());
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
			object.put("majorTypeName",majorOutList.get(i).getMajorTypeName());
			object.put("majorIntroduce", majorOutList.get(i).getMajorIntroduce());
			object.put("majorSubject", majorOutList.get(i).getMajorSubject());
			object.put("majorWork", majorOutList.get(i).getMajorWork());
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
			object.put("majorTypeName",majorLearnList.get(i).getMajorTypeName());
			object.put("majorIntroduce", majorLearnList.get(i).getMajorIntroduce());
			object.put("majorSubject", majorLearnList.get(i).getMajorSubject());
			object.put("majorWork", majorLearnList.get(i).getMajorWork());
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
			object.put("majorWant",majorList.get(i).getMajorWant());
			object.put("majorNeed",majorList.get(i).getMajorNeed());
			object.put("majorIntroduce", majorList.get(i).getMajorIntroduce());
			object.put("majorSubject", majorList.get(i).getMajorSubject());
			object.put("majorWork", majorList.get(i).getMajorWork());
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
