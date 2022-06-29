package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLJTDSConnection {
	public static Connection getSQLServerConnection() {
		// 4 biến này lấy khi cài đặt SQL Server => Sẽ có thay đổi
		String hostName = "localhost";
		String sqlInstanceName = "DESKTOP-H9245CO";
		String dbName = "BUH_COMPANY";
		String userName = "sa";
		String password = "minhhieu1710";
		return getSQLServerConnection(hostName, sqlInstanceName, dbName, userName, password);
	}
	
	private static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String dbName, String userName, String password) {
		Connection conn = null;
		try {
			// Cấu trúc URL Connection dành cho SQL Server
			String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/" + dbName + ";instance=" + sqlInstanceName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
