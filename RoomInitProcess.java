package control.stock.dbprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;

import assets.DBConnectionMgr;

public class RoomInitProcess {
public static void roomInfoInit(String str,int roomnum,int PCnum) {

		
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
			sql = "update roomInfo set in_out='X' where in_out='O' and roomnum="+roomnum+" and PCnum="+PCnum;
			sql2= "update roomInfo set end_time=? where roomnum="+roomnum+" and PCnum="+PCnum;
			pstmt = con.prepareStatement(sql);
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, str);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			pool.freeConnection(con, pstmt);
		}

	}
}
