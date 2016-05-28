package com.hk.db;

import com.hk.pojo.TweetPojo;
import java.util.*;

public interface TweetDB {
	public void putData(String str);
	public void putData(TweetPojo pojo);
	public String getData();
	public String getData(String id);
	public List<String> getAllTweet();

}
