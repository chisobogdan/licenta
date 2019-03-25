package com.java.licenta2018.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @desc A singleton database access class for Oracle DB
 * @author chiso
 */
public final class DBConnectionMySQL {
	public Connection conn;
	// private Statement statement;
	public static DBConnectionMySQL db;

	private DBConnectionMySQL() {
		String url = "jdbc:mysql://localhost:3306/demo";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
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
	public static synchronized DBConnectionMySQL getDbCon() {
		if (db == null) {
			db = new DBConnectionMySQL();
		}
		return db;
	}

}