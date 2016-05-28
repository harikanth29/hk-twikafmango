package com.hk.db;

import com.hk.pojo.Person;

public interface PersonDB {

	public void putData(String collection);
	public void getData(String collection);
	public void createPerson(Person per) ;
	
}
