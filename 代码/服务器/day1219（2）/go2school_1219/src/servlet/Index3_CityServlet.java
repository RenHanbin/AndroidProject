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

import bean.Index3_CityBean;
import dao.Index3_CityDao;


/**
 * Servlet implementation class Index3_CityServlet
 */
@WebServlet("/Index3_CityServlet")
public class Index3_CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index3_CityServlet() {
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
		System.out.println("index3cityServlet:remark="+remark);
		if("getIndex3CityList".equals(remark)) {
			getIndex3CityList(request,response);
		}
	}

	private void getIndex3CityList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Index3_CityDao index3cityDao=new Index3_CityDao();
		List<Index3_CityBean> index3cityList=index3cityDao.getIndex3_CityList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<index3cityList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("cityGdp",index3cityList.get(i).getCityGdp());
			object.put("cityName",index3cityList.get(i).getCityName());
			object.put("citySalary",index3cityList.get(i).getCitySalary());
			object.put("cityContent",index3cityList.get(i).getCityContent());
			object.put("cityType",index3cityList.get(i).getCityType());
			object.put("cityTitle",index3cityList.get(i).getCityTitle());
			object.put("Province",index3cityList.get(i).getProvince());
			object.put("cityImg",index3cityList.get(i).getCityImg());
			jsonArray.put(object);
		}
		System.out.println("Index3_CityServlet:index3cityList的长度为："+jsonArray.length());
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
