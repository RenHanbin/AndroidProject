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

import bean.PositionBean;
import dao.PositionDao;

/**
 * Servlet implementation class PositionServlet
 */
@WebServlet("/PositionServlet")
public class PositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PositionServlet() {
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
		System.out.println("positionServlet:remark="+remark);
		if("getPositionList".equals(remark)) {
			getPositionList(request,response);
		}
	}

	private void getPositionList(HttpServletRequest request, HttpServletResponse response) {
		PositionDao positionDao=new PositionDao();
		List<PositionBean> positionList=positionDao.getPositionList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<positionList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("positionId",positionList.get(i).getPositionId());
			object.put("positionName",positionList.get(i).getPositionName());
			jsonArray.put(object);
		}
		System.out.println("PositionServlet:arrayList的长度为："+jsonArray.length());
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
