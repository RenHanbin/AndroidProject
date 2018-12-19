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

import bean.CityTypeBean;
import dao.CityTypeDao;

/**
 * Servlet implementation class CityTypeServlet
 */
@WebServlet("/CityTypeServlet")
public class CityTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityTypeServlet() {
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
		System.out.println("CityTypeServlet:remark="+remark);
		if("getCityTypeList".equals(remark)) {
			getCityTypeList(request,response);
		}
	}
	private void getCityTypeList(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		CityTypeDao citytypeDao=new CityTypeDao();
		List<CityTypeBean> citytypeList=citytypeDao.getCityTypeList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<citytypeList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("cityId",citytypeList.get(i).getCityTypeId());
			object.put("cityName",citytypeList.get(i).getCityTypeName());
			jsonArray.put(object);
		}
		System.out.println("CityTypeServlet:arrayList的长度为："+jsonArray.length());
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
