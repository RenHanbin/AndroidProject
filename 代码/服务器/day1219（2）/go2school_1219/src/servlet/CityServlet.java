package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.CityBean;

import dao.CityDao;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityServlet() {
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
		System.out.println("CityServlet:remark="+remark);
		if("getCityJobsList".equals(remark)) {
			//地区就业
			getCityJobsList(request,response);
		}else if("getCityList".equals(remark)) {
			getCityList(request,response);
		}else if("getSearchCityJobsList".equals(remark)) {
			//地区就业查询
			String cityName=request.getParameter("cityName");
			System.out.println(cityName);
			getSearchCityJobsList(cityName,request,response);
		}else if("getSearchCityListByType".equals(remark)) {
			//地区就业查询2
			String cityTypeName=request.getParameter("cityTypeName");
			System.out.println(cityTypeName);
			getSearchCityListByType(cityTypeName,request,response);
		}else if("getSchoolNameByCity".equals(remark)) {
			String cityName=request.getParameter("cityName");
			CityDao cityDao = new CityDao();
			int Id = cityDao.getId(cityName);
			System.out.println(Id);
			String cityId = Id+"";
			getSchoolNameByCityId(request,response,cityId);
		}
	}
	
	private void getSchoolNameByCityId(HttpServletRequest request, HttpServletResponse response, String cityId) {
		// TODO Auto-generated method stub
		CityDao cityDao = new CityDao();
		List<CityBean> cityList = cityDao.getSchoolNameByCity(cityId);
		System.out.println("schoolServlet:city的长度为："+cityList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<cityList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolName",cityList.get(i).getSchoolName());
			object.put("schoolRank",cityList.get(i).getSchoolRank());
			object.put("schoolBestMajor", cityList.get(i).getSchoolBestMajor());
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
	//根据城市类别搜索城市
	private void getSearchCityListByType(String cityTypeName, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CityDao cityDao=new CityDao();
		List<CityBean> cityList=cityDao.getCityListByType(cityTypeName);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<cityList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("cityId",cityList.get(i).getCityId());
			object.put("cityName",cityList.get(i).getCityName());
			object.put("cityGdp",cityList.get(i).getCityGdp());
			object.put("cityContent",cityList.get(i).getCityContent());
			object.put("citySalary",cityList.get(i).getCitySalary());
			object.put("cityImg",cityList.get(i).getCityImg() );
			object.put("cityType",cityList.get(i).getCityType());
			object.put("province",cityList.get(i).getProvince());
			jsonArray.put(object);
		}
		System.out.println("CityJobsServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//根据城市名搜索城市
	private void getSearchCityJobsList(String cityName, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CityDao cityDao=new CityDao();
		List<CityBean> cityList=cityDao.getSearchCityList(cityName);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<cityList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("cityId",cityList.get(i).getCityId());
			object.put("cityName",cityList.get(i).getCityName());
			object.put("cityGdp",cityList.get(i).getCityGdp());
			object.put("cityContent",cityList.get(i).getCityContent());
			object.put("cityImg",cityList.get(i).getCityImg() );
			object.put("citySalary",cityList.get(i).getCitySalary());
			object.put("cityType",cityList.get(i).getCityType());
			object.put("province",cityList.get(i).getProvince());
			jsonArray.put(object);
		}
		System.out.println("CityJobsServlet:arrayList的长度为："+jsonArray.length());
		try {
			response.getWriter().append(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void getCityList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		// TODO Auto-generated method stub
			CityDao cityDao=new CityDao();
			List<CityBean> cityList=cityDao.getCityList();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<cityList.size();i++) {
				JSONObject object=new JSONObject();
				object.put("cityId",cityList.get(i).getCityId());
				object.put("cityName",cityList.get(i).getCityName());
				jsonArray.put(object);
			}
			System.out.println("CityServlet:arrayList的长度为："+jsonArray.length());
			try {
				response.getWriter().append(jsonArray.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	//就业方向
	private void getCityJobsList(HttpServletRequest request, HttpServletResponse response) {
		CityDao cityDao=new CityDao();
		List<CityBean> cityList=cityDao.getCityJobList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<cityList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("cityId",cityList.get(i).getCityId());
			object.put("cityName",cityList.get(i).getCityName());
			object.put("cityGdp",cityList.get(i).getCityGdp());
			object.put("cityContent",cityList.get(i).getCityContent());
			object.put("cityImg",cityList.get(i).getCityImg() );
			object.put("citySalary",cityList.get(i).getCitySalary());
			object.put("cityType",cityList.get(i).getCityType());
			object.put("province",cityList.get(i).getProvince());
			jsonArray.put(object);
		}
		System.out.println("CityJobsServlet:arrayList的长度为："+jsonArray.length());
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
