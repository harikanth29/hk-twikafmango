package com.hk.thread;

import com.hk.db.mongo.DBConnFactory;
import com.hk.db.mongo.DBFactory;


public class HKThread implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		DBConnFactory fac = DBConnFactory.getDBFactory();
		fac.getDBConnection(null, null);
		
		
	}
	
}