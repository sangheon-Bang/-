package control.stock.dbprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import assets.DBConnectionMgr;

public class RoomSearchProcess {
	public static void readRoom(DefaultTableModel dtm) {

		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		DBConnectionMgr pool = null;

		try {
			pool = DBConnectionMgr.getInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			// id, tel, mileage, age
			con = pool.getConnection();
			sql = "select * from pcbang.roominfo";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String roomnum = rs.getString("roomnum");
				String PCnum = rs.getString("PCnum");
				String roomRank = rs.getString("roomRank");
				String in_out =rs.getString("in_out");
				String end_time =rs.getString("end_time");
				String[] arr = { roomnum, PCnum, roomRank, in_out,end_time };
				dtm.addRow(arr);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			pool.freeConnection(con, pstmt);
		}

	}
}
