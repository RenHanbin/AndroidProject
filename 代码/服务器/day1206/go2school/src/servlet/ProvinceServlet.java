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


import bean.ProvinceBean;

import dao.ProvinceDao;

/**
 * Servlet implementation class ProvinceServlet
 */
@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvinceServlet() {
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
		System.out.println("provinceServlet:remark="+remark);
		if("getProvinceList".equals(remark)) {
			getProvinceList(request,response);
		}
	}

	private void getProvinceList(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		ProvinceDao provinceDao=new ProvinceDao();
		List<ProvinceBean> provinceList=provinceDao.getProvinceList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<provinceList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("provinceId",provinceList.get(i).getProvinceId());
			object.put("provinceName",provinceList.get(i).getProvinceName());
			jsonArray.put(object);
		}
		System.out.println("ProvinceServlet:arrayList的长度为："+jsonArray.length());
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
