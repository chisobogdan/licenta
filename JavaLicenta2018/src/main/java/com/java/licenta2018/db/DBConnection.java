package com.java.licenta2018.db;
import java.sql.*;

/**
 * @desc A singleton database access class for Oracle DB
 * @author chiso
 */
public final class DBConnection {
	public Connection conn;
	// private Statement statement;
	public static DBConnection db;

	private DBConnection() {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String driver = "oracle.jdbc.OracleDriver";
		String userName = "chiso";
		String password = "chiso";
		try {

			Class.forName(driver).newInstance();
			this.conn = (Connection) DriverManager.getConnection(url, userName,
					password);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 *
	 * @return OracleConnect Database connection object
	 */
	public static synchronized DBConnection getDbCon() {
		if (db == null) {
			db = new DBConnection();
		}
		return db;
	}

}