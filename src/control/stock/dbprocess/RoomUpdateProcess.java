package control.stock.dbprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;


import assets.DBConnectionMgr;

public class RoomUpdateProcess {
	public static void roomInfoChange(String time,int roomnum,int PCnum) {

		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		String sql2 =null;
		DBConnectionMgr pool = null;

		try {
			pool = DBConnectionMgr.getInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			con = pool.getConnection();
			sql = "update roomInfo set in_out='O' where in_out='X' and roomnum="+roomnum+" and PCnum="+PCnum;
			sql2= "update roomInfo set end_time=? where roomnum="+roomnum+" and PCnum="+PCnum;
			pstmt = con.prepareStatement(sql);
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, time);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			pool.freeConnection(con, pstmt);
		}

	}
	// 재고 조회메소드 종료
	
}
