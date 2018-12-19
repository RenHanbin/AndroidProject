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

import bean.RegionalEconomyBean;
import dao.RegionalEconomyDao;

/**
 * Servlet implementation class RegionalEconomyServlet
 */
@WebServlet("/RegionalEconomyServlet")
public class RegionalEconomyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegionalEconomyServlet() {
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
		String name=request.getParameter("name");
		System.out.println("regionaleconomyServlet:remark="+remark);
		if("getRegionalEconomyList".equals(remark)) {
			getRegionalEconomyList(request,response);
		}else if("getcityListByName".equals(remark)) {
			getcityListByName(request,response,name);
	}
	}

	private void getcityListByName(HttpServletRequest request, HttpServletResponse response, String name) {
		// TODO Auto-generated method stub
		RegionalEconomyDao regionaleconomy = new RegionalEconomyDao();
		List<RegionalEconomyBean> cityList = regionaleconomy.getCityNameByName(name);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<cityList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("collegeNum",cityList.get(i).getCollegeNum());
			object.put("cityGdp",cityList.get(i).getCityGdp());
			object.put("cityName",cityList.get(i).getCityName());
			object.put("citySalary",cityList.get(i).getCitySalary());
			object.put("cityContent",cityList.get(i).getCityContent());
			object.put("cityType",cityList.get(i).getCityType());
			object.put("cityTitle",cityList.get(i).getCityTitle());
			object.put("province",cityList.get(i).getProvince());
			object.put("cityImg",cityList.get(i).getCityImg());
			
			jsonArray.put(object);
		}
		System.out.println("regionaleconomyServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getRegionalEconomyList(HttpServletRequest request, HttpServletResponse response) {
		RegionalEconomyDao regionaleconomyDao=new RegionalEconomyDao();
		List<RegionalEconomyBean> regionaleconomyList=regionaleconomyDao.getRegionalEconomyList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<regionaleconomyList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("collegeNum",regionaleconomyList.get(i).getCollegeNum());
			object.put("cityGdp",regionaleconomyList.get(i).getCityGdp());
			object.put("cityName",regionaleconomyList.get(i).getCityName());
			object.put("citySalary",regionaleconomyList.get(i).getCitySalary());
			object.put("cityContent",regionaleconomyList.get(i).getCityContent());
			object.put("cityType",regionaleconomyList.get(i).getCityType());
			object.put("cityTitle",regionaleconomyList.get(i).getCityTitle());
			object.put("province",regionaleconomyList.get(i).getProvince());
			object.put("cityImg",regionaleconomyList.get(i).getCityImg());
			jsonArray.put(object);
		}
		System.out.println("RegionalEconomyServlet:regionaleconomyList的长度为："+jsonArray.length());
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
