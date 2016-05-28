package com.hk.db.mongo;

import com.hk.db.TweetDbConnection;
import com.hk.db.impl.TweetDbImpl;
import com.hk.db.utils.DBConstants;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DBConnFactory {

	@SuppressWarnings("resource")
	DB database = null;
	MongoClient mongoClient = null;
	private static DBConnFactory instance = null;

	public static DBConnFactory getDBFactory() {
		if (instance == null) {
			instance = new DBConnFactory();
		}
		return instance;
	}

	public DB getDBConnection(String dbname, String type) {
		System.out.println("Inside Getting DB..");
		try {
			if ("tweet".equalsIgnoreCase(type)) {
				database = TweetDbConnection.getConnection(dbname);
			} else if ("test".equalsIgnoreCase(type)) {
				database = TweetDbConnection.getConnection(dbname);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return database;
	}

	public DB getDBConnection(String dbname) {
		System.out.println("Inside Getting DB..");
		try {
			
			database = TweetDbConnection.getConnection(dbname);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return database;
	}
}
