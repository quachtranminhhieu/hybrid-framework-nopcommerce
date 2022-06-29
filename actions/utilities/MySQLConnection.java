package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
	public static Connection getMySQLConnection() {
		// 4 biến này lấy khi cài đặt MySQL => Sẽ có thay đổi
		String hostName = "localhost";
		String dbName = "automationfc";
		String userName = "root";
		String password = "123456";
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
		Connection conn = null;
		try {
			// Cấu trúc URL Connection dành cho MySQl
			// Ví dụ: jdbc:mysql://localhost:3306/automationfc
			// 3306 là port khi cài đặt MySQL, nếu có thay đổi thì trong đây phải cập nhật
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
