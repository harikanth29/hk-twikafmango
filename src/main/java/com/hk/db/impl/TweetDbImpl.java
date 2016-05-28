package com.hk.db.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hk.db.TweetDB;
import com.hk.db.mongo.DBConnFactory;
import com.hk.pojo.TweetPojo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class TweetDbImpl implements TweetDB {
	@Override
	public void putData(String str) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
				Locale.ENGLISH);
		DB db = null;
		try {
			db = DBConnFactory.getDBFactory().getDBConnection("tweet", "tweet");
			DBCollection dbs = db.getCollection("tweet");
			System.out.println("Count is:" + dbs.getCount());
			DBObject dbObject = (DBObject) JSON.parse(str);
			dbs.insert(dbObject);
			System.out.println("Record inserted Succesfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if(db!=null)
			// db.

		}
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void putData(TweetPojo pojo) {
		// TODO Auto-generated method stub
		DB db = null;
		try {
			db = DBConnFactory.getDBFactory().getDBConnection("tweet", "tweet");
			DBCollection dbs = db.getCollection("tweet");
			System.out.println("Count is:" + dbs.getCount());
			// BasicDBObjectBuilder dbo = BasicDBObjectBuilder.start();
			// DBObject dbObject = (DBObject) JSON.parse(str);
			// dbs.insert(dbObject);
			System.out.println("Record inserted Succesfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if(db!=null)
			// db.

		}
	}

	@Override
	public String getData(String id) {
		// TODO Auto-generated method stub
		DB db = null;
		String output = null;
		try {
			db = DBConnFactory.getDBFactory().getDBConnection("tweet", "tweet");
			DBCollection dbs = db.getCollection("tweet");
			System.out.println("Count is:" + dbs.getCount());
			DBObject obj = new BasicDBObject("id", id);
			DBCursor cur = dbs.find(obj);
			// System.out.println("Record inserted Succesfully");
			while (cur.hasNext()) {
				output = cur.next().toString();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if(db!=null)
			// db.

		}

		return output;
	}
	@Override
	public List<String> getAllTweet() {
		// TODO Auto-generated method stub
		DB db = null;
		String output = null;
		List<String> list=new ArrayList<>();
		try {
			db = DBConnFactory.getDBFactory().getDBConnection("tweet", "tweet");
			DBCollection dbs = db.getCollection("tweet");
			System.out.println("Count is:" + dbs.getCount());
			DBCursor cur = dbs.find();
			// System.out.println("Record inserted Succesfully");
			while (cur.hasNext()) {
				output = cur.next().toString();
			}
			list.add(output);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if(db!=null)
			// db.

		}

		return list;
	}
}
