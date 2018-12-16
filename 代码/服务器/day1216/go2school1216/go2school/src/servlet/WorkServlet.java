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

import bean.Work;
import dao.WorkDao;

/**
 * Servlet implementation class WorkServlet
 */
@WebServlet("/WorkServlet")
public class WorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String workMark = request.getParameter("workMark");
		System.out.println("workServlet:workMark="+workMark);
		if("getWorkList".equals(workMark)) {
			getWorkList(request,response);
		}
	}

	private void getWorkList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		WorkDao workDao = new WorkDao();
		List<Work> workList = workDao.getWorkList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<workList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("workId",workList.get(i).getWorkId());
			object.put("workName",workList.get(i).getWorkName());
			jsonArray.put(object);
		}
		System.out.println("workServlet:arrayList的长度为："+jsonArray.length());
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
