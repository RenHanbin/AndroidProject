package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.WriterBean;
import servlet.DataBase;

public class WriterDao {

	public WriterBean getWriterById(int writerId) throws SQLException {
		WriterBean writer = new WriterBean();
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from writer where writer_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				writer.setWriterId(rs.getInt(1));
				writer.setWriterName(rs.getString(2));
				writer.setWriterImage(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return writer;
	}

}
