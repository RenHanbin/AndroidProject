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

import bean.CollectionSchoolBean;
import bean.SchoolBean;
import dao.CollectionMajorDao;
import dao.CollectionSchoolDao;

/**
 * Servlet implementation class CollectionSchoolServlet
 */
@WebServlet("/CollectionSchoolServlet")
public class CollectionSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionSchoolServlet() {
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
		System.out.println("收藏学校servlet:remark="+remark);
		int userId=Integer.parseInt(request.getParameter("userId"));
		System.out.print("userId="+userId);
		//System.out.print("schoolId="+schoolId);
		if("addSchoolCollection".equals(remark)) {
			//添加收藏学校
			int schoolId=Integer.parseInt(request.getParameter("schoolId"));
			addSchoolCollection(userId,schoolId,request,response);
		}else if("deleteSchoolCollection".equals(remark)) {
			int schoolId=Integer.parseInt(request.getParameter("schoolId"));
			deleteSchoolCollection(userId,schoolId,request,response);
		}else if("ifExistCollectionSchool".equals(remark)) {
			//判断此专业是否已经被收藏过
			int schoolId=Integer.parseInt(request.getParameter("schoolId"));
			ifExistCollectionSchool(userId,schoolId,request,response);
		}else if("collectionSchoolListSelect".equals(remark)) {
			collectionSchoolListSelect(userId,request,response);
			
		}else if("collectionSchoolListDelete".equals(remark)) {
			//显示删除之后的收藏学校表
			int schoolId1=Integer.parseInt(request.getParameter("schoolId1"));
			collectionSchoolListDelete(userId,schoolId1,request,response);
			
		}else if("collectionSchoolListThree".equals(remark)) {
			//显示收藏学校的三级页面
			int schoolId1=Integer.parseInt(request.getParameter("schoolId1"));
			collectionSchoolListThree(schoolId1,request,response);
			
		}
	}

	//显示收藏学校的三级页面
	private void collectionSchoolListThree(int schoolId1, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionSchoolDao collectionschoolDao=new CollectionSchoolDao();
		SchoolBean schoolBean=collectionschoolDao.getSchoolListBySchoolId(schoolId1);
		JSONObject object=new JSONObject();
		object.put("schoolName", schoolBean.getSchoolName());
		object.put("schoolTypeName", schoolBean.getSchoolTypeName());
		object.put("schoolImg", schoolBean.getSchoolImg());
		object.put("schoolContent", schoolBean.getSchoolContent());
		object.put("schoolNumber", schoolBean.getSchoolNum());
		object.put("schoolBestMajor", schoolBean.getSchoolBestMajor());
		response.getWriter().append(object.toString());
	}

	//显示删除学校之后的收藏表
	private void collectionSchoolListDelete(int userId, int schoolId1, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionSchoolDao collectionschoolDao=new CollectionSchoolDao();
		List<SchoolBean> schoolList =new ArrayList<SchoolBean>();
		List<CollectionSchoolBean> deleteSchoolIdList=collectionschoolDao.deteleSchoolIdListByUserIdSchoolId(userId, schoolId1);
		for(int i=0;i<deleteSchoolIdList.size();i++) {
			System.out.println("deleteschoolId:"+deleteSchoolIdList.get(i).getSchoolId());
			int schoolId=deleteSchoolIdList.get(i).getSchoolId();
			schoolList.add(collectionschoolDao.getSchoolListBySchoolId(schoolId));
		}
		System.out.println("deleteschoolList.size():"+schoolList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId", schoolList.get(i).getSchoolId());
			object.put("schoolName", schoolList.get(i).getSchoolName());
			object.put("schoolRank", schoolList.get(i).getSchoolRank());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolTypeName", schoolList.get(i).getSchoolTypeName());
			jsonArray.put(object);
		}
		System.out.println("deleteCollectionSchoolServlet：jsonArray的长度为："+jsonArray.length());
		response.getWriter().append(jsonArray.toString());
	}

	private void collectionSchoolListSelect(int userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionSchoolDao collectionSchoolDao=new CollectionSchoolDao();
		List<SchoolBean> schoolList=new ArrayList<SchoolBean>();
		List<CollectionSchoolBean> collectionSchoolIdList=collectionSchoolDao.getSchoolIdListByUserId(userId);
		for(int i=0;i<collectionSchoolIdList.size();i++) {
			System.out.println("schoolId:"+collectionSchoolIdList.get(i).getSchoolId());
			int schoolId=collectionSchoolIdList.get(i).getSchoolId();
			schoolList.add(collectionSchoolDao.getSchoolListBySchoolId(schoolId));
		}
		System.out.println("schoolList.size():"+schoolList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<schoolList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("schoolId", schoolList.get(i).getSchoolId());
			object.put("schoolName", schoolList.get(i).getSchoolName());
			object.put("schoolRank", schoolList.get(i).getSchoolRank());
			object.put("schoolImg", schoolList.get(i).getSchoolImg());
			object.put("schoolTypeName", schoolList.get(i).getSchoolTypeName());
			jsonArray.put(object);
		}
		System.out.println("CollectionSchoolServlet：jsonArray的长度为："+jsonArray.length());
		response.getWriter().append(jsonArray.toString());
	}

	//判断此专业是否收藏过
		private void ifExistCollectionSchool(int userId, int schoolId, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
			CollectionSchoolDao collectionSchoolDao=new CollectionSchoolDao();
			boolean b=collectionSchoolDao.ifExist(userId,schoolId);
			JSONObject object=new JSONObject();
			if(b) {
				//已经收藏过
				System.out.println("收藏过");
				object.put("success", "已收藏");
			}else {
				//未收藏过
				System.out.println("没有搜藏过");
				object.put("false", "未收藏");
			}
			response.getWriter().append(object.toString());
		}

	
	//删除收藏学校
	private void deleteSchoolCollection(int userId, int schoolId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionSchoolDao collectionSchoolDao=new CollectionSchoolDao();
		boolean b=collectionSchoolDao.deleteCollectionSchool(userId,schoolId);
		JSONObject object=new JSONObject();
		if(b) {
			//注册成功
			System.out.println("取消收藏成功");
			object.put("success", "取消收藏成功");
		}else {
			//注册失败
			System.out.println("取消收藏失败");
			object.put("false", "取消收藏失败");
		}
		response.getWriter().append(object.toString());
	}

	//添加收藏学校
	private void addSchoolCollection(int userId, int schoolId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CollectionSchoolDao collectionSchoolDao=new CollectionSchoolDao();
		boolean b=collectionSchoolDao.addCollectionSchool(userId,schoolId);
		JSONObject object=new JSONObject();
		if(b) {
			//注册成功
			System.out.println("收藏成功");
			object.put("success", "收藏成功");
		}else {
			//注册失败
			System.out.println("收藏失败");
			object.put("false", "收藏失败");
		}
		response.getWriter().append(object.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
