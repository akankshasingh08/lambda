package com.simplecrud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Connection {

	private static final String MYSQLDRIVER = "com.mysql.cj.jdbc.Driver";
//	private static final String URL = "jdbc:mysql://sampledatabase.c7ihrepvlenc.us-east-1.rds.amazonaws.com:3306/sampledatabase";
//	private static final String DBUSER = "admin";
//	private static final String PASSWORD = "database";
	
	
	private static final String URL = "jdbc:mysql://database1.cfms0zyx5ihf.us-east-2.rds.amazonaws.com/database1";
	private static final String DBUSER = "admin";
	private static final String PASSWORD = "database1"; 
	
	

	private static final String loaclURL = "jdbc:mysql://localhost:3306/tooldb";
	private static final String localDBUSER = "root";
	private static final String localPASSWORD = "root";
	
	private static Connection conn = null;
	private static PreparedStatement stmt = null;

	static {
		try {
			System.out.println("Loading Driver....");
			Class.forName(MYSQLDRIVER);
			System.out.println("Driver laoded Successfully");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		
	}

	public static void test() {
		try {
			conn = DriverManager.getConnection(URL, DBUSER, PASSWORD);
			System.out.println(conn + "::::Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Test");
	}

	

	private static final String CREATE_TABLE_SQL = "CREATE TABLE database1.users  ("
			+ "user_id int(11) NOT NULL AUTO_INCREMENT," + "username varchar(45) NOT NULL,"
			+ "password varchar(45) NOT NULL," + "fullname varchar(45) NOT NULL," + "email varchar(45) NOT NULL,"
			+ "PRIMARY KEY (user_id))";

	public static void createTable() {

		try {
			conn = DriverManager.getConnection(URL, DBUSER, PASSWORD);

			stmt = conn.prepareStatement(CREATE_TABLE_SQL);

			stmt.executeUpdate(CREATE_TABLE_SQL);
			System.out.println("Table created successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertTable(){
		
		String sql = "INSERT INTO database1.users (username, password, fullname, email) VALUES (?, ?, ?, ?)";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, "bill");
			statement.setString(2, "secretpass");
			statement.setString(3, "Bill Gates");
			statement.setString(4, "bill.gates@microsoft.com");
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new user was inserted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static void select(){
		String sql = "SELECT * FROM database1.users";
		 
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery(sql);
			 
			int count = 0;
			 
			while (result.next()){
			    String name = result.getString(2);
			    String pass = result.getString(3);
			    String fullname = result.getString("fullname");
			    String email = result.getString("email");
			 
			    String output = "User #%d: %s - %s - %s - %s";
			    System.out.println(String.format(output, ++count, name, pass, fullname, email));
		} 
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	}
	
	

	// public static String createSVDetail(){
	// Connection conn = null;
	// PreparedStatement stmt = null;
	//
	// try{
	// conn = DriverManager.getConnection(loaclURL, localDBUSER, localPASSWORD);
	//
	// stmt = conn.prepareStatement(CREATE_TABLE_SQL);
	//
	// stmt.executeUpdate(CREATE_TABLE_SQL);
	// System.out.println("Table created successfully");
	// }
	// catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	//
	// }

}
