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

import bean.CollectionMajorBean;
import bean.MajorBean;
import dao.CollectionMajorDao;

/**
 * Servlet implementation class CollectionMajorServlet
 */
@WebServlet("/CollectionMajorServlet")
public class CollectionMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionMajorServlet() {
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
		System.out.println("收藏专业servlet:remark="+remark);
		int userId=Integer.parseInt(request.getParameter("userId"));
		int majorId=Integer.parseInt(request.getParameter("majorId"));
		System.out.print("userId="+userId);
		System.out.print("majorId="+majorId);
		if("addMajorCollection".equals(remark)) {
			//添加收藏专业
			addMajorCollection(userId,majorId,request,response);
		}else if("collectionMajorListSelect".equals(remark)) {
			//显示收藏专业
			collectionMajorListServlet(userId,request,response);	
		}else if("collectionMajorListDelete".equals(remark)) {
			//删除收藏专业
			collectionMajorListDelete(userId,request,response);
		}else if("deleteMajorCollection".equals(remark)) {
			deleteMajorCollection(userId,majorId,request,response);
		}
	}

	//删除收藏专业
	private void deleteMajorCollection(int userId, int majorId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionMajorDao collectionMajorDao=new CollectionMajorDao();
		boolean b=collectionMajorDao.deleteCollectionMajor(userId,majorId);
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

	//删除收藏专业后显示
	private void collectionMajorListDelete(int userId,HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int majorId1=Integer.parseInt(request.getParameter("majorId1"));
		CollectionMajorDao collectionMajorDao=new CollectionMajorDao();
		List<MajorBean> majorList =new ArrayList<MajorBean>();
		List<CollectionMajorBean> deleteMajorIdList=collectionMajorDao.deteleMajorIdListByUserIdMajorId(userId, majorId1);
		for(int i=0;i<deleteMajorIdList.size();i++) {
			System.out.println("deletemajorId:"+deleteMajorIdList.get(i).getMajorId());
			int majorId2=deleteMajorIdList.get(i).getMajorId();
			majorList.add(collectionMajorDao.getMajorListByMajorId(majorId2));
		}
		System.out.println("deletemajorList.size():"+majorList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<majorList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorName",majorList.get(i).getMajorName());
			object.put("majorTypeName",majorList.get(i).getMajorTypeName());
			object.put("majorId", majorList.get(i).getMajorId());
			jsonArray.put(object);
		}
		System.out.println("deleteCollectionMajorServlet：jsonArray的长度为："+jsonArray.length());
		response.getWriter().append(jsonArray.toString());
	}

	//添加收藏专业
	private void addMajorCollection(int userId, int  majorId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionMajorDao collectionMajorDao=new CollectionMajorDao();
		boolean b=collectionMajorDao.addCollectionMajor(userId, majorId);
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

	//显示收藏专业
	private void collectionMajorListServlet(int userId,HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CollectionMajorDao collectionMajorDao=new CollectionMajorDao();
		List<MajorBean> majorList =new ArrayList<MajorBean>();
		List<CollectionMajorBean> collectionMajorIdList=collectionMajorDao.getMajorListByUserId(userId);//参数是userId
		for(int i=0;i<collectionMajorIdList.size();i++) {
			System.out.println("majorId:"+collectionMajorIdList.get(i).getMajorId());
			int majorId1=collectionMajorIdList.get(i).getMajorId();
			majorList.add(collectionMajorDao.getMajorListByMajorId(majorId1));
		}
		System.out.println("majorList.size():"+majorList.size());
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<majorList.size();i++) {
			JSONObject object=new JSONObject();
			object.put("majorName",majorList.get(i).getMajorName());
			object.put("majorTypeName",majorList.get(i).getMajorTypeName());
			object.put("majorId", majorList.get(i).getMajorId());
			jsonArray.put(object);
		}
		System.out.println("CollectionMajorServlet：jsonArray的长度为："+jsonArray.length());
		response.getWriter().append(jsonArray.toString());
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
