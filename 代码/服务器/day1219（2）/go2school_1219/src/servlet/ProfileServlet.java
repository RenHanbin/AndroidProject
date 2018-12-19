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

import bean.ProfileBean;
import dao.ProfileDao;




/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
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
		System.out.println("profileServlet:remark="+remark);
		if("getProfileList".equals(remark)) {
			getProfileList(request,response);
		}
	}
	private void getProfileList(HttpServletRequest request, HttpServletResponse response) {
		ProfileDao profileDao=new ProfileDao();
		List<ProfileBean> profileList=profileDao.getProfileList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<profileList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolName",profileList.get(i).getSchoolName());
			object.put("schoolRank",profileList.get(i).getSchoolRank());
			object.put("schoolBestMajor",profileList.get(i).getSchoolBestMajor());
			jsonArray.put(object);
		}
		System.out.println("ProfileServlet:profileList的长度为："+jsonArray.length());
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
