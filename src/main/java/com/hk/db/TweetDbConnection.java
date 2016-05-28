package com.hk.db;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class TweetDbConnection {

	private static DB db = null;

	private TweetDbConnection() {
		
	}

	public static DB getConnection(String dbname) {
		Mongo mg = new Mongo("localhost", 27017);
		if (db == null)
			db = mg.getDB(dbname);
		System.out.println("Connect to database successfully");
		return db;
	}
}
