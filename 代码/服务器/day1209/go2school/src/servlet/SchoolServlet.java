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
		System.out.println("schoolServlet:schoolMark="+schoolMark);
		if("getSchoolPaiList".equals(schoolMark)) {
			getSchoolPairList(request,response);
		}
	}

	private void getSchoolPairList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SchoolDao schoolDao = new SchoolDao();
		List<SchoolBean> schoolList = schoolDao.getSchoolPaiMing();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
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
