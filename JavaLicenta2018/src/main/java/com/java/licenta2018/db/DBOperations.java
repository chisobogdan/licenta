package com.java.licenta2018.db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBOperations {

	DBConnectionMySQL conn = DBConnectionMySQL.getDbCon();
	
	/**
	 * preluare nume localitati distincte
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getDistinctLocalitati() throws SQLException {
		ArrayList<String> numeLocalitati = new ArrayList<String>();
		String query = "Select distinct nume from localitati";
		Statement statement = conn.conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			String nume = rs.getString("Nume");
			numeLocalitati.add(nume);
		}
		return numeLocalitati;
	}
	
	/**
	 * preluare id zona in functie de locatie
	 * @param localitate
	 * @return
	 * @throws SQLException
	 */
	public int getZoneByLocation(String localitate) throws SQLException {
		String query = "Select distinct id_zona from localitati where nume='"
				+ localitate + "'";
		Statement statement = conn.conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		int id = rs.getInt("id_zona");

		return id;
	}
	
	/**
	 * preluare sisteme ale unei locatii
	 * @param localitate
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getDistinctSisteme(String localitate)
			throws SQLException {
		ArrayList<String> sisteme = new ArrayList<String>();
		String query = "Select distinct s.nume from sisteme s, localitati l where s.id = l.id_sistem and l.nume = '"
				+ localitate + "'";
		Statement statement = conn.conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			String nume = rs.getString("Nume");
			sisteme.add(nume);
		}
		return sisteme;
	}

}
