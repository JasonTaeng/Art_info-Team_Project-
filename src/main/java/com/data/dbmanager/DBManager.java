package com.data.dbmanager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {
	public static Connection conn;
	public DBManager() {super();}
	
	public Connection getConnection() {		
		try {
			Context init = new InitialContext();
			Context env = (Context)init.lookup("java:/comp/env");
			DataSource db = (DataSource)env.lookup("jdbc/data");
			conn = db.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
