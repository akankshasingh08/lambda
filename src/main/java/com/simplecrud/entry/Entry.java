package com.simplecrud.entry;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.simplecrud.db.DB_Connection;



public class Entry implements RequestHandler<Map, String> {

	public String handleRequest(Map input, Context context) {
		DB_Connection.test();
//		DB_Connection.createTable();
//		DB_Connection.insertTable();
		DB_Connection.select();
		return "Success";
	}
	
	public static void main(String[] args){
//		DB_Connection.test();
//		DB_Connection.createTable();
//		dbConnection.insertdata("swqdqw",2,"sssw");
	}

	
}