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
import bean.ProvinceStudent;
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
		}else if("getProvinceLineList".equals(remark)) {
			String provinceName=request.getParameter("provinceName");
			String studentType=request.getParameter("studentType");
			System.out.println(provinceName);
			System.out.print(studentType);
			getProvinceLineList(provinceName,studentType,request,response);
		}
	}

	//得到省分数线
	private void getProvinceLineList(String provinceName, String studentType, HttpServletRequest request, HttpServletResponse response) {
		ProvinceDao provinceDao=new ProvinceDao();
		ProvinceStudent ps= provinceDao.getLine(studentType, provinceName);
		JSONObject object=new JSONObject();
		object.put("provinceId", ps.getProvinceId());
		object.put("studentTypeId", ps.getStudentTypeId());
		object.put("firstLine", ps.getFirstGradeLine());
		object.put("secondLine", ps.getSecondGradeLine());
		object.put("thirdLine", ps.getThirdGradeLine());
		object.put("otherLine", ps.getOtherGradeLine());
		try {
			response.getWriter().append(object.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//得到省列表
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
