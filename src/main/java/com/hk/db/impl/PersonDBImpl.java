package com.hk.db.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.db.TweetDbConnection;
import com.hk.db.PersonDB;
import com.hk.db.mongo.DBConnFactory;
import com.hk.pojo.Person;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.connection.Connection;
import com.mongodb.util.JSON;

public class PersonDBImpl implements PersonDB {

	public PersonDBImpl() {

	}

	public void connectDB() {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
				Locale.ENGLISH);
		DB db = null;
		try {

			// BasicDBObject ob = new BasicDBObject("Name", "Harikanth");
			// BasicDBObject ob1 = new BasicDBObject("Name", "Bruahth");
			//
			// DBCollection dbc = db.getCollection("persondetail");
			// List<BasicDBObject> lt = new ArrayList<BasicDBObject>();
			// lt.add(ob);
			// lt.add(ob1);
			// ob.put("Name", "HK");
			db = TweetDbConnection.getConnection(null);
			DBCollection dbs = db.getCollection("restaurent");
			// FindIterable<Document> iterable =
			// db.getCollection("restaurants").find();
			System.out.println("Count is:" + dbs.getCount());
			String str = "{'address': {'building': '7190', 'coord': [-73.985174, 40.727336], 'street': '1St Ave', 'zipcode': '10009'}, 'borough': 'Manhattan', 'cuisine': 'Bakery', 'grades': [{'date': {'$date': 1421712000000}, 'grade': 'Not Yet Graded', 'score': 0}], 'name': 'Sweet  Generation', 'restaurant_id': '50018399'}";
			DBObject dbObject = (DBObject) JSON.parse(str);
			// DBCursor dc = dbs.find(("building","4537"));
			dbs.insert(dbObject);
			// dbc.insert(ob);
			// db.getCollection("person")
			// .insert(new BasicDBObject("Name", "Hari"));
			System.out.println("Person inserted");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// if(db!=null)

		}
	}

	public void getData(String coll) {

		// DBCollection dbs = db.getCollection(coll);
		// FindIterable<Document> iterable =
		DB db = TweetDbConnection.getConnection(null);
		DBCollection dbs = db.getCollection(coll);
		System.out.println("Count is:" + dbs.getCount());
		// DBCursor dc = dbs.find(new BasicDBObject("building", 4537));
		DBCursor dc = dbs.find();
		// DBObject dbo = null;
		Iterator<DBObject> ite = dc.iterator();
		while (ite.hasNext()) {
			BasicDBObject ls = (BasicDBObject) dc.next().get("Employee");
			System.out.println(ls.toString());
			// System.out.println(dc.next().get("Company"));
		}
	}

	public void putData(String str) {

		Mongo mg = new Mongo("localhost", 27017);
		// DB db = mg.getDB("hk");
		String dbName = "test";
		String type = "test";
		DB db = null;
		DBCollection dbs = null;
		try {
			db = DBConnFactory.getDBFactory().getDBConnection(dbName, type);
			dbs = db.getCollection(type);
			System.out.println("Connect to database" + dbName + "successfully");

			// try {

			// BasicDBObject ob = new BasicDBObject("Name", "Harikanth");
			// BasicDBObject ob1 = new BasicDBObject("Name", "Bruahth");
			// String str =
			// "{'Employee':{{'Name':'Harikanth'},{'Company':'Apple'},{'mobile':'5124677'}}}";
			// String str1 =
			// "{'Employee':{{'Name':'Bruahth'},{'Company':'Apple'},{'mobile':'12345'}}}";
			// DBCollection/ dbs = db.getCollection(col);

			DBObject dbObject = (DBObject) JSON.parse(str);
			// DBObject dbObject1 = (DBObject) JSON.parse(str1);
			// dbs.insert(dbObject);
			dbs.insert(dbObject);

			System.out.println("Person inserted");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// dbs.

		}
	}
	public void createPerson(Person per) {

		Mongo mg = new Mongo("localhost", 27017);
		ObjectMapper mapper = new ObjectMapper();
		// DB db = mg.getDB("hk");
		String dbName = "person";
		DB db = null;
		DBCollection dbs = null;
		try {
			db = DBConnFactory.getDBFactory().getDBConnection(dbName);
			dbs = db.getCollection(dbName);
			System.out.println("Connect to database" + dbName + "successfully");
			DBObject dbObject = (DBObject) JSON.parse(mapper.writeValueAsString(per));
			dbs.insert(dbObject);

			System.out.println("Person inserted");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// dbs.

		}
	}

	public static void main(String args[]) {

		PersonDBImpl im = new PersonDBImpl();
		// im.connectDB();
		// im.putData("Employee");
		im.getData("Employee");
	}

}
