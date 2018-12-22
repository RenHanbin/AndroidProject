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

import bean.SchoolBean;
import dao.SchoolDao;

/**
 * Servlet implementation class SchoolServlet
 */
@WebServlet("/SchoolServlet")
public class SchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String schoolMark=request.getParameter("schoolMark");
		String name=request.getParameter("name");
		String workName = request.getParameter("workName");
		System.out.println("schoolServlet:schoolMark="+schoolMark);
		if("getSchoolPaiList".equals(schoolMark)) {
			getSchoolPairList(request,response);
		}else if("getSchoolPaiListByName".equals(schoolMark)) {
			getSchoolPairListByName(request,response,name);
		}else if("getSchoolWorkList".equals(schoolMark)) {
			getSchoolWorkList(request,response);
		}else if("getSchoolWorkListByWorkId".equals(schoolMark)) {
			SchoolDao schoolDao = new SchoolDao();
			int Id = schoolDao.getId(workName);
			String workId = Id+"";
			getSchoolWorkListByWorkId(request,response,workId);
		}else if("getSchoolNum".equals(schoolMark)) {
			getSchoolNum(request,response);
		}else if("getSchoolNumByTypeName".equals(schoolMark)) {
			SchoolDao schoolDao = new SchoolDao();
			String typeName = request.getParameter("typeName");
			int id = schoolDao.getSchoolTypeId(typeName);
			getSchoolNumById(id,request,response);
		}else if("getSchoolAll".equals(schoolMark)) {
			getSchoolAll(request,response);
		}else if("getRank".equals(schoolMark)) {
			String grade = request.getParameter("grade");
			int score = Integer.parseInt(grade);
			String year = request.getParameter("year");
			String province = request.getParameter("province");
			getRank(score,year,province,request,response);
		}else if("getSchool".equals(schoolMark)) {
			String pai = request.getParameter("paiming");
			int paiming = Integer.parseInt(pai);
			getSchool(paiming,request,response);
		}else if("getSchool2".equals(schoolMark)) {
			String pai = request.getParameter("paiming");
			int paiming = Integer.parseInt(pai);
			getSchool2(paiming,request,response);
		}else if("getSchool3".equals(schoolMark)) {
			String pai = request.getParameter("paiming");
			int paiming = Integer.parseInt(pai);
			getSchool3(paiming,request,response);
		}
	}
	

	//估分择校（保底）
	private void getSchool3(int paiming, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchool3(paiming);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("need",schoolList.get(i).getNeed());
			object.put("lowGrade",schoolList.get(i).getLow_grade());
			object.put("lowRank",schoolList.get(i).getLow_rank());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//估分择校（稳一稳）
	private void getSchool2(int paiming, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchool2(paiming);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("need",schoolList.get(i).getNeed());
			object.put("lowGrade",schoolList.get(i).getLow_grade());
			object.put("lowRank",schoolList.get(i).getLow_rank());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//估分择校（冲一冲）
	private void getSchool(int paiming, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchool(paiming);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("need",schoolList.get(i).getNeed());
			object.put("lowGrade",schoolList.get(i).getLow_grade());
			object.put("lowRank",schoolList.get(i).getLow_rank());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//估分择校（名次）
	private void getRank(int score, String year, String province, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		String rank = schoolDao.getRank(score, year, province);
		JSONObject object=new JSONObject();
		object.put("rank",rank);
		try {
			response.getWriter().append(object.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//估分择校（所有学校）
	private void getSchoolAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolAll();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("need",schoolList.get(i).getNeed());
			object.put("lowGrade",schoolList.get(i).getLow_grade());
			object.put("lowRank",schoolList.get(i).getLow_rank());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//大学介绍
	private void getSchoolNum(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolNum();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//大学介绍（筛选）
	private void getSchoolNumById(int id, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolNumById(id);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//就业方向（筛选）
	private void getSchoolWorkListByWorkId(HttpServletRequest request, HttpServletResponse response, String workId) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolWorkListByWork(workId);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("cityName",schoolList.get(i).getCityName());
			object.put("salary", schoolList.get(i).getSalary());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//就业方向
	private void getSchoolWorkList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolWorkList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("cityName",schoolList.get(i).getCityName());
			object.put("salary", schoolList.get(i).getSalary());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//大学排名（搜索）
	//大学排名（搜索）
	private void getSchoolPairListByName(HttpServletRequest request, HttpServletResponse response,String name) {
		// TODO Auto-generated method stub
		
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolPaiMingByName(name);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("cityName",schoolList.get(i).getCityName());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//大学排名
	//大学排名
	private void getSchoolPairList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolPaiMing();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId",schoolList.get(i).getSchoolId());
			object.put("schoolRank",schoolList.get(i).getSchoolRank());
			object.put("cityId",schoolList.get(i).getCityId());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolContent", schoolList.get(i).getSchoolContent());
			object.put("schoolNum", schoolList.get(i).getSchoolNum());
			object.put("schoolTypeId",schoolList.get(i).getSchoolTypeId());
			object.put("schoolTypeName",schoolList.get(i).getSchoolTypeName());
			object.put("schoolMajor", schoolList.get(i).getSchoolBestMajor());
			object.put("schoolName",schoolList.get(i).getSchoolName());
			object.put("cityName",schoolList.get(i).getCityName());
			jsonArray.put(object);
		}
		System.out.println("schoolServlet:arrayList的长度为："+jsonArray.length());
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
