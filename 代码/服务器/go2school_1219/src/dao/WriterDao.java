package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Article;
import bean.Writer;
import servlet.DataBase;

public class WriterDao {

	//根据writerId返回对应writer数组
	public Writer getWriterById(int writerId) {
		Writer writer=new Writer();
		Connection conn=null;
		try {
			conn = DataBase.getConnection();
			PreparedStatement pre=null;
			ResultSet res=null;
			String sql="select writer_id,writer_name,writer_img from writer where writer_id=?";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, writerId);
			res=pre.executeQuery();
			while(res.next()) {
				System.out.print("WriterDao:已经查询出根据作者id找到作者对象");
				writer.setWriterId(writerId);
				writer.setWriterName(res.getString("writer_name"));
				writer.setWriterImg(res.getString("writer_img"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer;
	}
	
	
}
