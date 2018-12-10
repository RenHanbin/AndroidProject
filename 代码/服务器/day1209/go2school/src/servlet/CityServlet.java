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
		if("getCityList".equals(remark)) {
			getCityList(request,response);
		}
	}
	private void getCityList(HttpServletRequest request, HttpServletResponse response){
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
